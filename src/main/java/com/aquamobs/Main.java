package com.aquamobs;

import com.aquamobs.commands.GameModeCommand;
import com.aquamobs.config.ServerConfig;
import com.aquamobs.events.PlayerJoinHandler;
import com.aquamobs.world.WorldManager;
import net.minestom.server.MinecraftServer;

public class Main {
    public static void main(String[] args) {
        // Initialize the server
        MinecraftServer server = MinecraftServer.init();

        // Set up world and lighting
        WorldManager.setupWorld(server);

        // Register event handlers
        PlayerJoinHandler.registerEvents(server);

        // Register commands
        GameModeCommand.registerCommands(server);

        // Start the server with configuration
        ServerConfig config = new ServerConfig();
        server.start(config.getHost(), config.getPort());
    }
}