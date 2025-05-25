package com.aquamobs.events;

import com.aquamobs.util.logger.LogType;
import com.aquamobs.util.logger.Logger;
import com.aquamobs.world.WorldManager;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;

public class PlayerJoinHandler {
    public static void registerEvents(MinecraftServer server) {
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            Player player = event.getPlayer();
            event.setSpawningInstance(WorldManager.getInstanceContainer());
            player.setRespawnPoint(WorldManager.getSpawnPosition());
            player.setGameMode(GameMode.CREATIVE); // for testing reasons

            Logger.log("Player joined: " + player.getUuid(), LogType.INFO);
        });
    }
}