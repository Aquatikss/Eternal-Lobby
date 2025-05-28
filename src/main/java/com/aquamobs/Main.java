package com.aquamobs;

import com.aquamobs.commands.GameModeCommand;
import com.aquamobs.commands.ServerCommand;
import com.aquamobs.config.ServerConfig;
import com.aquamobs.data.ServerManager;
import com.aquamobs.events.event.AsyncPlayerConfigurationEventHandler;
import com.aquamobs.gui.ChestGUI;
import com.aquamobs.world.WorldManager;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;

public class Main {
    public static void main(String[] args) {
        // Initialize the server
        MinecraftServer server = MinecraftServer.init();

        // Set up world and lighting
        WorldManager.setupWorld(server);

        // Initialize server manager
        ServerManager.init();

        // Set up the global event handler
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();

        // Register event handlers
        AsyncPlayerConfigurationEventHandler.registerEvents(globalEventHandler);

        // Register commands
        GameModeCommand.registerCommands(server);
        ServerCommand.registerCommands(server);

        // Initialize GUI event listeners
        ChestGUI.init();

        // Start the server with configuration
        ServerConfig config = new ServerConfig();
        server.start(config.getHost(), config.getPort());
    }
}