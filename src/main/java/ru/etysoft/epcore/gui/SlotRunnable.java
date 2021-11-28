package ru.etysoft.epcore.gui;

import org.bukkit.entity.Player;

public class SlotRunnable implements Runnable {
    @Override
    public void run() {

    }
    private Player sender;
    private GUITable guiTable;

    public void setGUITable(GUITable guiTable) {
        this.guiTable = guiTable;
    }

    public GUITable getGUITable() {
        return guiTable;
    }

    public void setSender(Player sender)
    {
        this.sender = sender;
    }

    public Player getSender() {
        return sender;
    }


}
