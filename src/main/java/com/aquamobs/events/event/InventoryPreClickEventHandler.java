package com.aquamobs.events.event;

import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.inventory.InventoryPreClickEvent;

public class InventoryPreClickEventHandler {
    public static void registerEvents(GlobalEventHandler globalEventHandler) {
        globalEventHandler.addListener(InventoryPreClickEvent.class, event -> {
            event.setCancelled(true);
        });
    }
}
