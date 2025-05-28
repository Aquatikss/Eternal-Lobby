package com.aquamobs.events.event;

import com.aquamobs.events.RegisterEvents;
import com.aquamobs.util.logger.LogType;
import com.aquamobs.util.logger.Logger;
import com.aquamobs.world.WorldManager;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class AsyncPlayerConfigurationEventHandler {
    public static void registerEvents(GlobalEventHandler globalEventHandler) {
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            Player player = event.getPlayer();
            event.setSpawningInstance(WorldManager.getInstanceContainer());
            player.setRespawnPoint(WorldManager.getSpawnPosition());
            player.setGameMode(GameMode.CREATIVE); // for testing reasons

            Logger.log("Player " + player.getUsername() + "(" + player.getUuid() + ") has joined with an IP of " + player.getPlayerConnection().getRemoteAddress() + "!", LogType.INFO);

            MiniMessage miniMessage = MiniMessage.miniMessage();

            ItemStack serverCompass = ItemStack.builder(Material.COMPASS)
                    .customName(miniMessage.deserialize("<!italic><gold>Server Selector"))
                    .lore(miniMessage.deserialize("<!italic><gray>Click to open the server selector"))
                    .build();

            player.getInventory().setItemStack(4, serverCompass);
            RegisterEvents.registerEvents(globalEventHandler);
        });
    }
}