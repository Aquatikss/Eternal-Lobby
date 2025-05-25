package com.aquamobs.commands;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentEnum;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.command.builder.arguments.ArgumentType;

public class GameModeCommand {
    public static void registerCommands(MinecraftServer server) {
        Command gameModeCommand = new Command("gamemode", "gm");

        ArgumentEnum<GameMode> modeArg = ArgumentType.Enum("mode", GameMode.class)
                .setFormat(ArgumentEnum.Format.LOWER_CASED); 

  
        gameModeCommand.setDefaultExecutor((sender, context) -> {
            sender.sendMessage("Usage: /gamemode <survival|creative>");
        });

        gameModeCommand.addSyntax((sender, context) -> {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be executed by a player!");
                return;
            }

            Player player = (Player) sender;
            GameMode mode = context.get(modeArg);

            player.setGameMode(mode);
            player.sendMessage("Game mode changed to " + mode.name().toLowerCase() + "!");
        }, modeArg);

        server.getCommandManager().register(gameModeCommand);
    }
}