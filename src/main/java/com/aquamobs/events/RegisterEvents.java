package com.aquamobs.events;

import com.aquamobs.events.event.InventoryPreClickEventHandler;
import com.aquamobs.events.event.ItemDropEventHandler;
import com.aquamobs.events.event.PlayerSwapItemEventHandler;
import net.minestom.server.event.GlobalEventHandler;

public class RegisterEvents {
    public static void registerEvents(GlobalEventHandler globalEventHandler) {
        InventoryPreClickEventHandler.registerEvents(globalEventHandler);
        ItemDropEventHandler.registerEvents(globalEventHandler);
        PlayerSwapItemEventHandler.registerEvents(globalEventHandler);
    }
}
