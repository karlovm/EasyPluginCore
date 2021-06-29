package ru.etysoft.epcore.gui;

import org.bukkit.inventory.ItemStack;

public class Slot {

    private SlotRunnable onClick;
    private ItemStack item;

    public Slot(SlotRunnable onClick, ItemStack item)
    {
        this.onClick = onClick;
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }

    public SlotRunnable getOnClick() {
        return onClick;
    }
}