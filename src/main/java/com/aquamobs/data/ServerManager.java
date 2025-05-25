package com.aquamobs.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.player.PlayerChatEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerManager {
    private static final File SERVER_FILE = new File("data/servers.json");
    private static List<Server> servers = new ArrayList<>();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void init() {
        if (!SERVER_FILE.getParentFile().exists()) {
            SERVER_FILE.getParentFile().mkdirs();
        }

        if (SERVER_FILE.exists()) {
            try (Reader reader = new FileReader(SERVER_FILE)) {
                servers = GSON.fromJson(reader, new TypeToken<List<Server>>(){}.getType());
                if (servers == null) servers = new ArrayList<>();
            } catch (IOException e) {
                MinecraftServer.getExceptionManager().handleException(e);
                servers = new ArrayList<>();
            }
        } else {
            saveServers();
        }

        MinecraftServer.getGlobalEventHandler().addListener(PlayerChatEvent.class, ServerCreationData::handleChat);
    }

    public static void addServer(Server server) {
        servers.add(server);
    }

    public static void saveServers() {
        try (Writer writer = new FileWriter(SERVER_FILE)) {
            GSON.toJson(servers, writer);
        } catch (IOException e) {
            MinecraftServer.getExceptionManager().handleException(e);
        }
    }

    public static List<Server> getServers() {
        return servers;
    }
}