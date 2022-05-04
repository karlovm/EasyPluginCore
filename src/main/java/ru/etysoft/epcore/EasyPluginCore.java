package ru.etysoft.epcore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;


public final class EasyPluginCore extends JavaPlugin {

    private static EasyPluginCore instance;
    private static String version = "0.6";
    private static ArrayList<String> supportedVersions = new ArrayList<>();

    public static String getAPIVersion() {
        return version;
    }

    public static boolean isSupported(String apiVersion)
    {
        return supportedVersions.contains(apiVersion);
    }

    @Override
    public void onEnable() {
        instance = this;


        supportedVersions.add("0.4");
        supportedVersions.add("0.5");
        supportedVersions.add("0.6");
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
