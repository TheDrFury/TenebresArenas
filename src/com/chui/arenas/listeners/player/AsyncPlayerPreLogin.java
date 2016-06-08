package com.chui.arenas.listeners.player;

import com.chui.arenas.Arenas;
import com.chui.arenas.handlers.Game;
import com.chui.arenas.listeners.ArenaListener;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class AsyncPlayerPreLogin extends ArenaListener {
    public AsyncPlayerPreLogin(Arenas pl) {
        super(pl);
    }

    @EventHandler
    public void playerPreLogin(AsyncPlayerPreLoginEvent e) {
        if (Game.hasStarted())
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatColor.RED + "Game Currently in Progress");
    }
}
