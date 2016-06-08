package com.chui.arenas.listeners.inventory;

import com.chui.arenas.Arenas;
import com.chui.arenas.handlers.Kit;
import com.chui.arenas.listeners.ArenaListener;
import com.chui.arenas.utils.ChatUtilities;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClick extends ArenaListener{
    public InventoryClick(Arenas pl) {
        super(pl);
    }
    public void onInventoryClick(InventoryClickEvent e) {
        if (! (e.getWhoClicked() instanceof Player && e.getInventory().getName().equalsIgnoreCase("Kit Selector")) )
            return;
        e.setCancelled(true);
        Player player = (Player) e.getWhoClicked();
        ItemStack i = e.getCurrentItem();
        if (i.getType() == null || i.getType()== Material.AIR)
            return;

        Kit k = Kit.getKit(i.getItemMeta().getDisplayName());
        Kit.setKit(player, k);
        ChatUtilities.sendMessage(player, ChatColor.GREEN + "Kit: " + k.getName() + " selected!");
    }
}
