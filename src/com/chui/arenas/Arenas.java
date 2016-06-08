package com.chui.arenas;

import com.chui.arenas.handlers.Kit;
import com.chui.arenas.listeners.player.AsyncPlayerPreLogin;
import com.chui.arenas.listeners.player.PlayerDeath;
import com.chui.arenas.listeners.player.PlayerJoin;
import com.chui.arenas.listeners.player.PlayerQuit;
import com.chui.arenas.threads.StartCountdown;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.Arrays;

public class Arenas extends JavaPlugin{

    public static int startCountdownId;

    public static Permission permission = null;
    public static Economy economy = null;
    public static Chat chat = null;

    public void onEnable() {
        GameState.setState(GameState.IN_LOBBY);
        System.out.println("TenebresArenas Enabled!");
        setupConfig();
        registerKits();
        registerListeners();
        setupPermissions();
        startCountdown();
    }

    public void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new PlayerQuit(this), this);
        pm.registerEvents(new AsyncPlayerPreLogin(this), this);
        pm.registerEvents(new PlayerDeath(this), this);
    }

    public void setupConfig() {
        FileConfiguration config = getConfig();

        config.set("Kits.Basic.Items", Arrays.asList("272", "279:5"));
        config.set("Kits.Basic.Display Item", 272);
        config.set("Kits.Warrior.Items", Arrays.asList("72", "27:5"));
        config.set("Kits.Warrior.Display Item", 1);
        saveConfig();

    }

    public void registerKits() {
        FileConfiguration config = getConfig();
        for (String s : config.getConfigurationSection("Kits").getKeys(false)) {
            String path = "Kits." + s + ".";
            new Kit(s, config.getStringList(path + "Items"), config.getInt(path + "Display Item"));
        }
    }
    @SuppressWarnings("deprecation")
    public void startCountdown() {
        StartCountdown.timeUntilStart = 60;
        startCountdownId = getServer().getScheduler().scheduleSyncRepeatingTask(this, new StartCountdown(this), 20l, 20l);

    }

    public void stopCountdown() {
        getServer().getScheduler().cancelTask(startCountdownId);
    }

    public void restartCountdown() {
        stopCountdown();
        startCountdown();
    }



    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    private boolean setupChat()
    {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

}
