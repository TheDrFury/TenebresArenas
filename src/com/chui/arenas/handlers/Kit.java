package com.chui.arenas.handlers;

import com.chui.arenas.Arenas;
import com.chui.arenas.utils.ChatUtilities;
import com.chui.arenas.utils.InventoryUtilities;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Kit {

    private static List<Kit> allKits = new ArrayList<Kit>();
    private List<ItemStack> items = new ArrayList<ItemStack>();
    private static HashMap<String, Kit> playerKits = new HashMap<String, Kit>();
    private String name;
    private static String permission;

    private int displayItem;
    @SuppressWarnings("deprecation")
    public Kit(String name, List<String> items, int displayItem) {
        this.name = name;
        this.permission = "tArenas.kit." + name;
        this.displayItem = displayItem;

        for (String s : items) {
            int id = 0, amount = 1;
            if (s.contains(":")) {
                String[] splitItem = s.split(":");
                id = Integer.valueOf(splitItem[0].trim());
                amount = Integer.valueOf(splitItem[1].trim());
            } else
                id = Integer.valueOf(s.trim());
            this.items.add(new ItemStack(id, amount));
        }
    }

    public static boolean isKit(String name) {
        for (Kit k : allKits)
            if (name == k.getName())
                return true;
        return false;
    }

    public static Kit getKit(String name) {
        for (Kit k : allKits)
            if (k.getName().equalsIgnoreCase(name))
                return k;
        return null;
    }

    public static void setKit(Player player, Kit kit) {
        if (!Arenas.permission.has(player, permission)) {
            ChatUtilities.sendMessage(player, "No permission for this kit!");
            return;
        }
        playerKits.put(player.getName(), kit);
    }

    public static Kit getKit(Player player) {
        return playerKits.get(player.getName()==null ? allKits.get(0) : playerKits.get(player.getName()));
    }

    public void setKit(Player player) {
        setKit(player, this);
    }

    public void giveKit(Player player) {
        InventoryUtilities.clearInventory(player);
        for (ItemStack is : items)
            player.getInventory().addItem(is);
    }

    public String getName() {
        return name;
    }
    @SuppressWarnings("deprecation")
    public ItemStack getDisplayItem() {
        ItemStack is = new ItemStack(displayItem);
        ItemMeta im = is.getItemMeta();

        is.setItemMeta(im);
        return is;
    }

    public static List<Kit> getAllKits() {
        return allKits;
    }


}
