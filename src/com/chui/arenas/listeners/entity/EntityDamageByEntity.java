package com.chui.arenas.listeners.entity;

import com.chui.arenas.Arenas;
import com.chui.arenas.handlers.Game;
import com.chui.arenas.handlers.Team;
import com.chui.arenas.listeners.ArenaListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity extends ArenaListener {

    public EntityDamageByEntity(Arenas pl) {
        super(pl);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player && e.getDamager() instanceof Player)) {
            e.setCancelled(true);
            return;
        }
        if (!Game.hasStarted()) {
            e.setCancelled(true);
            return;
        }

        Player player = (Player) e.getEntity();
        Player damager = (Player) e.getDamager();

        if (Team.getTeam(player) == Team.getTeam(damager)) {
            e.setCancelled(true);
            damager.sendMessage(ChatColor.GRAY + "You may not attack that player!");
        }

    }
}
