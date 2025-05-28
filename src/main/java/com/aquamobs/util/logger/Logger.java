package com.aquamobs.util.logger;

import org.fusesource.jansi.Ansi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.fusesource.jansi.Ansi.Color.*;

public class Logger {

    public static void log(String message, LogType logType) {
        System.out.println(formatMessage(message, logType));
    }

    private Logger() {} // Prevent instantiation

    private static String colorizePrefix(LogType logType) {
        Ansi.Color color = switch (logType) {
            case INFO -> BLUE;
            case WARNING -> YELLOW;
            case ERROR -> RED;
            case DEBUG -> GREEN;
            default -> WHITE;
        };
        return Ansi.ansi().fg(color).toString() + "[" + logType.getType() + "]" + Ansi.ansi().reset().toString();
    }

    private static String formatMessage(String message, LogType logType) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String dateString = now.format(formatter);

        message = String.format("[%s] %s", dateString, message);
        return colorizePrefix(logType) + " " + message;
    }
}