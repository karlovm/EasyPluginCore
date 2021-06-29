package ru.etysoft.epcore;

import org.bukkit.ChatColor;

import java.util.List;

public class TextManager {
    public static String toColor(String text) {
        try {
            return  ChatColor.translateAlternateColorCodes('&', text);
        } catch (Exception e) {
            return text;
        }
    }

    public static List<String> toColor(List<String> text) {
        for(int i = 0; i < text.size(); i++)
        {
            String updated = toColor(text.get(i));
            text.set(i, updated);
        }
        return text;
    }


}
