package com.chui.arenas.threads;


import com.chui.arenas.Arenas;
import com.chui.arenas.handlers.Game;
import com.chui.arenas.utils.ChatUtilities;
import org.bukkit.scheduler.BukkitRunnable;

public class StartCountdown extends BukkitRunnable {

    Arenas plugin;

    public StartCountdown(Arenas pl) {
        plugin = pl;
    }

    public static int timeUntilStart;

    public void run() {

        if (timeUntilStart == 0) {
            if (!Game.canStart()) {
                plugin.restartCountdown();
                ChatUtilities.broadcast("Unable to start game. Restarting countdown!");
                return;
            }
            Game.start();

        }


        if (timeUntilStart % 10 == 0 || timeUntilStart < 10) {
            ChatUtilities.broadcast(String.valueOf(timeUntilStart) + " seconds until game starts!");
        }
        timeUntilStart--;
    }

}