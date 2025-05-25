package com.aquamobs.commands;

import com.aquamobs.data.ServerCreationData;
import com.aquamobs.gui.ChestGUI;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;

public class ServerCommand {
    public static void registerCommands(MinecraftServer server) {
        Command serverCommand = new Command("server");

        // /server list
        serverCommand.addSyntax((sender, context) -> {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be executed by a player!");
                return;
            }
            Player player = (Player) sender;
            ChestGUI.openServerList(player);
        }, ArgumentType.Literal("list"));

        // /server create
        serverCommand.addSyntax((sender, context) -> {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be executed by a player!");
                return;
            }
            Player player = (Player) sender;
            ServerCreationData.startCreation(player);
            ChestGUI.openServerCreation(player);
        }, ArgumentType.Literal("create"));

        // Command Registration
        server.getCommandManager().register(serverCommand);
    }
}