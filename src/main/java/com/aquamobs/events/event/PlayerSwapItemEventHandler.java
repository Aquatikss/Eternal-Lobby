package com.aquamobs.events.event;

import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerSwapItemEvent;

public class PlayerSwapItemEventHandler {
    public static void registerEvents(GlobalEventHandler globalEventHandler) {
        globalEventHandler.addListener(PlayerSwapItemEvent.class, event -> {;
            // Prevent offhand item swap
            event.setCancelled(true);
        });
    }
}
