package com.chui.arenas.listeners.player;

import com.chui.arenas.Arenas;
import com.chui.arenas.handlers.Game;
import com.chui.arenas.handlers.Kit;
import com.chui.arenas.listeners.ArenaListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class PlayerInteract extends ArenaListener{
    public PlayerInteract(Arenas pl) {
        super(pl);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getItem() == null || e.getItem().getType() == Material.AIR || e.getItem().getType() == null)
            return;
        if (e.getItem().getType()==Material.COAL && !Game.hasStarted()) {
            Inventory inv = Bukkit.createInventory(null, 27,"Kit Selector");
            for (Kit k : Kit.getAllKits())
                inv.addItem(k.getDisplayItem());
            e.getPlayer().openInventory(inv);
        }
    }
}
