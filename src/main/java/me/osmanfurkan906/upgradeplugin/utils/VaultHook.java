package me.osmanfurkan906.upgradeplugin.utils;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class VaultHook {
    private Economy economy;

    public VaultHook(JavaPlugin plugin) {
        PluginManager pluginManager = Bukkit.getPluginManager();
        if (!pluginManager.isPluginEnabled("Vault")) {
            pluginManager.disablePlugin(plugin);
            return;
        }

        this.economy = this.setupEconomy();
    }

    public Economy getEconomy() {
        return this.economy;
    }

    private Economy setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        return rsp.getProvider();
    }

}
