package com.aquamobs.events.event;

import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.ItemDropEvent;;

public class ItemDropEventHandler {
    public static void registerEvents(GlobalEventHandler globalEventHandler) {
        globalEventHandler.addListener(ItemDropEvent.class, event -> {;
            event.setCancelled(true);
        });
    }
}
