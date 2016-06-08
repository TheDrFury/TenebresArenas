package com.chui.arenas.listeners.player;

import com.chui.arenas.Arenas;
import com.chui.arenas.GameState;
import com.chui.arenas.handlers.Game;
import com.chui.arenas.handlers.Team;
import com.chui.arenas.listeners.ArenaListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit extends ArenaListener{

    public PlayerQuit(Arenas pl) {
        super(pl);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        if (GameState.isState(GameState.IN_LOBBY))
            Game.setCanStart(Bukkit.getOnlinePlayers().size()-1 >= 8);

        Player player = e.getPlayer();

        if (Game.hasStarted())
            Team.getTeam(player).remove(player);
    }

}
