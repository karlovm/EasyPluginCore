package ru.etysoft.epcore;

import org.bukkit.Bukkit;

public class Console {

    public static void sendMessage(String coloredString)
    {
        Bukkit.getConsoleSender().sendMessage(TextManager.toColor(coloredString));
    }

    public static void sendWarning(String text)
    {
        Bukkit.getLogger().warning(text);
    }
}
