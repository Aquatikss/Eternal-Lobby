package com.aquamobs.config;

public class ServerConfig {
    private static final String HOST = "0.0.0.0";
    private static final int PORT = 25674;
    private static final String VELOCITY_SECRET = "SGrcXe0jvGOq";

    public String getHost() {
        return HOST;
    }

    public int getPort() {
        return PORT;
    }

    public String getVelocitySecret() {
        return VELOCITY_SECRET;
    }
}