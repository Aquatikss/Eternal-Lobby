package com.aquamobs.util.logger;

public enum LogType {

    INFO("INFO"),
    WARNING("WARNING"),
    ERROR("ERROR"),
    DEBUG("DEBUG"),
    DEFAULT("NONE");

    private final String type;

    LogType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
