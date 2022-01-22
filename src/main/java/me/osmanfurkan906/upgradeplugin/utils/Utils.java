package me.osmanfurkan906.upgradeplugin.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Utils {
    public static String colored(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String getMessage(FileConfiguration config, String path) {
        return colored(config.getString("messages." + path));
    }
}
