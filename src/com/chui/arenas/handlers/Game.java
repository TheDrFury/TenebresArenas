package com.chui.arenas.handlers;

import com.chui.arenas.utils.LocationUtilities;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Game {

    private static boolean canStart = false;
    private static boolean hasStarted = false;

    public static void setCanStart(boolean b) {
        canStart = b;
    }

    public static boolean canStart() {
        return canStart;
    }

    public static boolean hasStarted() {
        return hasStarted;
    }

    public static void start(){
        new Team("Red");
        new Team("Blue");

        int i = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (i >= Team.getAllTeams().size())
                i = 0;
            Team.getAllTeams().get(i).add(player);
            LocationUtilities.teleportToGame(player, Team.getAllTeams().get(i));
            Kit.getKit(player).giveKit(player);
            i++;
        }
    }

    public static void stop(Team team) {
        hasStarted = false;
    }

}
