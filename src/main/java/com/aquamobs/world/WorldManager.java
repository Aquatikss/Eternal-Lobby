package com.aquamobs.world;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;

public class WorldManager {
    private static InstanceContainer instanceContainer;

    public static void setupWorld(MinecraftServer server) {
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        instanceContainer = instanceManager.createInstanceContainer();

        instanceContainer.setChunkSupplier(LightingChunk::new);

        instanceContainer.setGenerator(new CustomGenerator());
    }

    public static InstanceContainer getInstanceContainer() {
        return instanceContainer;
    }

    public static Pos getSpawnPosition() {
        return new Pos(0, 42, 0);
    }
}