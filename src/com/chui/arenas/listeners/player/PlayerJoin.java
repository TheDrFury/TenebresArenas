package com.chui.arenas.listeners.player;

import com.chui.arenas.Arenas;
import com.chui.arenas.handlers.Game;
import com.chui.arenas.listeners.ArenaListener;
import com.chui.arenas.utils.InventoryUtilities;
import com.chui.arenas.utils.LocationUtilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerJoin extends ArenaListener{
    public PlayerJoin(Arenas pl) {
        super(pl);
    }
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Game.setCanStart(Bukkit.getOnlinePlayers().size() >= 8);
        LocationUtilities.teleportToSpawn(e.getPlayer());
        InventoryUtilities.clearInventory(player);

        ItemStack is = new ItemStack(Material.COAL);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GREEN + "Kits");

        player.getInventory().addItem(is);
        player.updateInventory();
    }


}
