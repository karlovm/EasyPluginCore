package ru.etysoft.epcore.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Slot {

    private SlotRunnable onClick;
    private SlotListener slotListener;
    private ItemStack item;

    public Slot(SlotRunnable onClick, ItemStack item)
    {
        this.onClick = onClick;
        this.item = item;
    }

    public Slot(SlotListener slotListener, ItemStack item)
    {
        this.slotListener = slotListener;
        this.item = item;
    }

    public SlotListener getSlotListener() {
        return slotListener;
    }

    public ItemStack getItem() {
        return item;
    }

    public SlotRunnable getOnClick() {
        return onClick;
    }

    public interface SlotListener
    {
         void onRightClicked(Player sender, GUITable guiTable);
         void onLeftClicked(Player sender, GUITable guiTable);
         void onShiftClicked(Player sender, GUITable guiTable);
    }
}