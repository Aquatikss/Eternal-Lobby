package com.aquamobs.data;

import com.aquamobs.gui.ChestGUI;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerChatEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ServerCreationData {
    private static final Map<UUID, ServerCreationData> creationDataMap = new HashMap<>();

    private final Player player;
    private String name;
    private String ip;
    private boolean bedrockSupport;
    private String baseVersion;
    private String viaVersionRange;
    private String description;
    private String discordInvite;
    private boolean cracked;
    private CreationStep step;

    private enum CreationStep {
        NAME, IP, BASE_VERSION, VIA_VERSION, DESCRIPTION, DISCORD_INVITE, DONE
    }

    public ServerCreationData(Player player) {
        this.player = player;
        this.bedrockSupport = false;
        this.cracked = false;
        this.step = CreationStep.NAME;
    }

    public static void startCreation(Player player) {
        creationDataMap.put(player.getUuid(), new ServerCreationData(player));
        player.sendMessage("Enter the server name:");
    }

    public static void handleChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        ServerCreationData data = creationDataMap.get(player.getUuid());
        if (data == null) return;

        String message = event.getRawMessage().trim();

        switch (data.step) {
            case NAME:
                if (message.isEmpty()) {
                    player.sendMessage("Name cannot be empty! Try again:");
                    return;
                }
                data.name = message;
                data.step = CreationStep.IP;
                player.sendMessage("Enter the server IP (e.g., localhost:25565):");
                break;
            case IP:
                if (message.isEmpty()) {
                    player.sendMessage("IP cannot be empty! Try again:");
                    return;
                }
                data.ip = message;
                data.step = CreationStep.BASE_VERSION;
                player.sendMessage("Enter the server base version (e.g., 1.20):");
                break;
            case BASE_VERSION:
                if (message.isEmpty()) {
                    player.sendMessage("Base version cannot be empty! Try again:");
                    return;
                }
                data.baseVersion = message;
                data.step = CreationStep.VIA_VERSION;
                player.sendMessage("Enter the ViaVersion range (e.g., 1.8-1.20):");
                break;
            case VIA_VERSION:
                if (message.isEmpty()) {
                    player.sendMessage("ViaVersion range cannot be empty! Try again:");
                    return;
                }
                data.viaVersionRange = message;
                data.step = CreationStep.DESCRIPTION;
                player.sendMessage("Enter the server description:");
                break;
            case DESCRIPTION:
                if (message.isEmpty()) {
                    player.sendMessage("Description cannot be empty! Try again:");
                    return;
                }
                data.description = message;
                data.step = CreationStep.DISCORD_INVITE;
                player.sendMessage("Enter the Discord invite (or type 'skip' to skip):");
                break;
            case DISCORD_INVITE:
                data.discordInvite = message.equalsIgnoreCase("skip") ? "" : message;
                data.step = CreationStep.DONE;
                player.sendMessage("Server creation completed! Open the GUI to save.");
                ChestGUI.openServerCreation(player);
                break;
        }
    }

    public static ServerCreationData get(Player player) { return creationDataMap.get(player.getUuid()); }
    public static void remove(Player player) { creationDataMap.remove(player.getUuid()); }

    public void save() {
        Server server = new Server(name, ip, bedrockSupport, baseVersion, viaVersionRange, description, discordInvite, cracked, player.getUsername());
        ServerManager.addServer(server);
        ServerManager.saveServers();
        player.sendMessage("Server '" + name + "' created successfully!");
        creationDataMap.remove(player.getUuid());
    }

    public boolean isBedrockSupport() { return bedrockSupport; }
    public void setBedrockSupport(boolean bedrockSupport) { this.bedrockSupport = bedrockSupport; }
    public boolean isCracked() { return cracked; }
    public void setCracked(boolean cracked) { this.cracked = cracked; }
    public boolean isDone() { return step == CreationStep.DONE; }
}