package ru.etysoft.epcore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class EasyPluginCore extends JavaPlugin {

    private static EasyPluginCore instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getConsoleSender().sendMessage(TextManager.toColor("&6EPC API " + this.getDescription().getVersion() +" &7started successfully."));

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(TextManager.toColor("&6EPC API &7disabled successfully."));
    }

    public static EasyPluginCore getInstance() {
        return instance;
    }
}
