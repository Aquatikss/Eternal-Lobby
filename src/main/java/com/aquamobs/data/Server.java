package com.aquamobs.data;

public class Server {
    private String name;
    private String ip;
    private boolean bedrockSupport;
    private String baseVersion;
    private String viaVersionRange;
    private String description;
    private String discordInvite;
    private boolean cracked;
    private String creator;

   
    public Server() {}

    public Server(String name, String ip, boolean bedrockSupport, String baseVersion, String viaVersionRange,
                  String description, String discordInvite, boolean cracked, String creator) {
        this.name = name;
        this.ip = ip;
        this.bedrockSupport = bedrockSupport;
        this.baseVersion = baseVersion;
        this.viaVersionRange = viaVersionRange;
        this.description = description;
        this.discordInvite = discordInvite;
        this.cracked = cracked;
        this.creator = creator;
    }

    
    public String getName() { return name; }
    public String getIp() { return ip; }
    public boolean isBedrockSupport() { return bedrockSupport; }
    public String getBaseVersion() { return baseVersion; }
    public String getViaVersionRange() { return viaVersionRange; }
    public String getDescription() { return description; }
    public String getDiscordInvite() { return discordInvite; }
    public boolean isCracked() { return cracked; }
    public String getCreator() { return creator; }
}