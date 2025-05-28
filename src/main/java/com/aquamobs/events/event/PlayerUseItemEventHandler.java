package com.aquamobs.events.event;

import com.aquamobs.gui.ChestGUI;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.tag.Tag;

public class PlayerUseItemEventHandler {
    public static void registerEvents(GlobalEventHandler globalEventHandler) {
        globalEventHandler.addListener(PlayerUseItemEvent.class, event -> {
            Player player = event.getPlayer();
            ItemStack item = event.getItemStack();

            // Check if the item is a compass with the server_selector tag
            if (item.material() == Material.COMPASS) {
                String selectorTag = item.getTag(Tag.String("server_selector"));
                if ("true".equals(selectorTag)) {
                    // Directly open the server list GUI
                    ChestGUI.openServerList(player);
                    event.setCancelled(true); // Prevent further interaction
                }
            }
        });
    }
}