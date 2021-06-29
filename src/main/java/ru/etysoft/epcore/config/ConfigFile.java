package ru.etysoft.epcore.config;

import org.bukkit.plugin.java.JavaPlugin;
import ru.etysoft.epcore.EasyPluginCore;
import ru.etysoft.epcore.TextManager;

import java.util.List;

public class ConfigFile {

    private JavaPlugin instance;

    public ConfigFile(JavaPlugin instance)
    {
        this.instance = instance;
    }

    public int getIntFromConfig(String key)
    {
        return instance.getConfig().getInt(key);
    }

    public  boolean getBooleanFromConfig(String key)
    {
        return instance.getConfig().getBoolean(key);
    }

    public String[] getStringsFromConfigAsArray(String key)
    {
        List<String> list = getStringsFromConfig(key);
        String[] arr = list.toArray(new String[list.size()]);
        return arr;
    }

    public List<String> getStringsFromConfig(String key)
    {
        return TextManager.toColor(instance.getConfig().getStringList(key));
    }

    public String getStringFromConfig(String key)
    {
        return  TextManager.toColor(instance.getConfig().getString(key));
    }

    public String getPrefixedStringFromConfig(String key)
    {
        return  TextManager.toColor(instance.getConfig().getString("prefix") + instance.getConfig().getString(key));
    }
}
