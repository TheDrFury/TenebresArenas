package com.chui.arenas.listeners.player;

import com.chui.arenas.Arenas;
import com.chui.arenas.handlers.Team;
import com.chui.arenas.listeners.ArenaListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath extends ArenaListener{
    public PlayerDeath(Arenas pl) {
        super(pl);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Team.getTeam(player).remove(player);

        player.kickPlayer(ChatColor.RED + "You died!");
    }
}
