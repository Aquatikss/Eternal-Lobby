package com.aquamobs.gui;

import com.aquamobs.data.Server;
import com.aquamobs.data.ServerCreationData;
import com.aquamobs.data.ServerManager;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.kyori.adventure.text.Component;
import java.util.Arrays;
import java.util.List;

public class ChestGUI {
    private static final String SERVER_LIST_TITLE = "Server List";
    private static final String SERVER_CREATION_TITLE = "Create Server";

    public static void init() {
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(InventoryPreClickEvent.class, event -> {
            registerEvent(event);
        });
    }

    private static void registerEvent(InventoryPreClickEvent event) {
        Player player = event.getPlayer();
        if (event.getInventory() instanceof Inventory) {
            Inventory inventory = (Inventory) event.getInventory();
            String title = inventory.getTitle().toString();

            if (title.equals(SERVER_LIST_TITLE)) {

                event.setCancelled(true);

                int clickedSlot = event.getSlot();
                if (clickedSlot >= 0 && clickedSlot < ServerManager.getServers().size()) {
                    Server server = ServerManager.getServers().get(clickedSlot);
                    player.sendMessage("Connecting to " + server.getName() + " (" + server.getIp() + ")...");
                    player.closeInventory();
                }
            } else if (title.equals(SERVER_CREATION_TITLE)) {

                event.setCancelled(true);

                ServerCreationData creationData = ServerCreationData.get(player);
                if (creationData == null) return;

                int slot = event.getSlot();
                defineSlotInteractions(slot, player, creationData);
            }
        }
    }

    private static void defineSlotInteractions(int slot, Player player, ServerCreationData creationData) {
        switch (slot) {
            case 0: // Name
                ServerCreationData.startCreation(player);
                player.closeInventory();
                break;
            case 1: // IP
                creationData = ServerCreationData.get(player);
                if (creationData != null) {
                    player.sendMessage("Enter the server IP (e.g., localhost:25565):");
                    player.closeInventory();
                }
                break;
            case 2: // Bedrock Support Toggle
                creationData.setBedrockSupport(!creationData.isBedrockSupport());
                openServerCreation(player);
                break;
            case 3: // Base Version
                creationData = ServerCreationData.get(player);
                if (creationData != null) {
                    player.sendMessage("Enter the server base version (e.g., 1.20):");
                    player.closeInventory();
                }
                break;
            case 4: // ViaVersion Range
                creationData = ServerCreationData.get(player);
                if (creationData != null) {
                    player.sendMessage("Enter the ViaVersion range (e.g., 1.8-1.20):");
                    player.closeInventory();
                }
                break;
            case 5: // Description
                creationData = ServerCreationData.get(player);
                if (creationData != null) {
                    player.sendMessage("Enter the server description:");
                    player.closeInventory();
                }
                break;
            case 6: // Discord Invite
                creationData = ServerCreationData.get(player);
                if (creationData != null) {
                    player.sendMessage("Enter the Discord invite (or type 'skip' to skip):");
                    player.closeInventory();
                }
                break;
            case 7: // Cracked Toggle
                creationData.setCracked(!creationData.isCracked());
                openServerCreation(player);
                break;
            case 8: // Save
                if (!creationData.isDone()) {
                    player.sendMessage("Please complete all fields before saving!");
                    return;
                }
                creationData.save();
                player.closeInventory();
                break;
            default:
                player.sendMessage("Invalid slot clicked: " + slot);
                break;
        }
    }

    public static void openServerList(Player player) {
        Inventory inventory = new Inventory(InventoryType.CHEST_6_ROW, Component.text(SERVER_LIST_TITLE));
        int slot = 0;
        for (Server server : ServerManager.getServers()) {
            if (slot >= 54) break;
            ItemStack glassPane = ItemStack.of(Material.BLACK_STAINED_GLASS_PANE)
                    .withCustomName(Component.text(server.getName()))
                    .withLore(Arrays.asList(
                            Component.text("IP: " + server.getIp()),
                            Component.text("Bedrock Support: " + (server.isBedrockSupport() ? "Yes" : "No")),
                            Component.text("Base Version: " + server.getBaseVersion()),
                            Component.text("ViaVersion Range: " + server.getViaVersionRange()),
                            Component.text("Description: " + server.getDescription()),
                            Component.text(server.getDiscordInvite().isEmpty() ? "Discord: None" : "Discord: " + server.getDiscordInvite()),
                            Component.text("Cracked: " + (server.isCracked() ? "Yes" : "No")),
                            Component.text("Creator: " + server.getCreator()),
                            Component.text("Click to join!")
                    ));
            inventory.setItemStack(slot++, glassPane);
        }
        player.openInventory(inventory);
    }

    public static void openServerCreation(Player player) {
        ServerCreationData data = ServerCreationData.get(player);
        if (data == null) {
            ServerCreationData.startCreation(player);
            data = ServerCreationData.get(player);
        }

        Inventory inventory = new Inventory(InventoryType.CHEST_1_ROW, Component.text(SERVER_CREATION_TITLE));

        inventory.setItemStack(0, ItemStack.of(Material.PAPER)
                .withCustomName(Component.text("Set Name"))
                .withLore(List.of(Component.text("Click to set the server name via chat"))));

        inventory.setItemStack(1, ItemStack.of(Material.OAK_SIGN)
                .withCustomName(Component.text("Set IP"))
                .withLore(List.of(Component.text("Click to set the server IP via chat"))));

        inventory.setItemStack(2, ItemStack.of(data.isBedrockSupport() ? Material.LIME_STAINED_GLASS_PANE : Material.RED_STAINED_GLASS_PANE)
                .withCustomName(Component.text("Bedrock Support: " + (data.isBedrockSupport() ? "Yes" : "No")))
                .withLore(List.of(Component.text("Click to toggle"))));

        inventory.setItemStack(3, ItemStack.of(Material.BOOK)
                .withCustomName(Component.text("Set Base Version"))
                .withLore(List.of(Component.text("Click to set the base version via chat"))));

        inventory.setItemStack(4, ItemStack.of(Material.COMPASS)
                .withCustomName(Component.text("Set ViaVersion Range"))
                .withLore(List.of(Component.text("Click to set the version range via chat"))));

        inventory.setItemStack(5, ItemStack.of(Material.WRITTEN_BOOK)
                .withCustomName(Component.text("Set Description"))
                .withLore(List.of(Component.text("Click to set the description via chat"))));

        inventory.setItemStack(6, ItemStack.of(Material.WHITE_BANNER)
                .withCustomName(Component.text("Set Discord Invite"))
                .withLore(List.of(Component.text("Click to set the Discord invite via chat (optional)"))));

        inventory.setItemStack(7, ItemStack.of(data.isCracked() ? Material.IRON_INGOT : Material.COAL)
                .withCustomName(Component.text("Cracked: " + (data.isCracked() ? "Yes" : "No")))
                .withLore(List.of(Component.text("Click to toggle"))));

        inventory.setItemStack(8, ItemStack.of(Material.EMERALD)
                .withCustomName(Component.text("Save Server"))
                .withLore(List.of(Component.text("Click to save the server"))));

        player.openInventory(inventory);
    }
}