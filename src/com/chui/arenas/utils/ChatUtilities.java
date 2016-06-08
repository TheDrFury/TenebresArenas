package com.chui.arenas.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class ChatUtilities {

    public static void broadcast(String message) {
        Bukkit.broadcastMessage(starter() + message);
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(starter() + message);
    }

    private static String starter() {
        return DARK_GRAY + "[" + RED + "Tenebres" + DARK_GRAY + "]" + WHITE;
    }
}
