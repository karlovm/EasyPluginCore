package ru.etysoft.epcore.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import ru.etysoft.epcore.EasyPluginCore;
import ru.etysoft.epcore.TextManager;

import java.util.HashMap;

public class GUITable implements Listener {

    Inventory inventory;
    HashMap<Integer, Slot> matrix;
    int lines;
    int maxSize;

    private Runnable onClosed;

    private JavaPlugin instance;

    private boolean closable;



    public GUITable(String title, int lines, HashMap<Integer, Slot> matrix, JavaPlugin instance, Material airPlaceholder, boolean closable) throws Exception
    {
        if(lines > 6)
        {
            throw new Exception("Lines cannot be more than 6!");
        }
        else
        {
            this.closable = closable;
            this.lines = lines;
            maxSize = lines * 9;
            this.instance = instance;
            inventory = Bukkit.createInventory(null, lines * 9, title);
            Bukkit.getPluginManager().registerEvents(this, instance);
            this.matrix = matrix;
            initializeItems(airPlaceholder);
        }

    }


    public void setOnClosed(Runnable onClosed) {
        this.onClosed = onClosed;
    }

    public boolean isClosable() {
        return closable;
    }

    public void setClosable(boolean closable) {
        this.closable = closable;
    }

    private void initializeItems(Material airPlaceholder) throws Exception {
        for(int i = 0; i < maxSize; i++)
        {
            inventory.setItem(i, new ItemStack(airPlaceholder));
        }


        for(int index : matrix.keySet())
        {
            Slot slot = matrix.get(index);
            inventory.setItem(index - 1, slot.getItem());
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory() != inventory) return;
        e.setCancelled(true);
        int clickedId = e.getRawSlot();
        if(matrix.containsKey(clickedId + 1)) {
            final Player p = (Player) e.getWhoClicked();
            Slot slot = matrix.get(clickedId + 1);
            if(slot.getSlotListener() == null) {
                SlotRunnable slotRunnable = slot.getOnClick();
                slotRunnable.setSender(p);
                slotRunnable.setGUITable(this);
                slotRunnable.run();
            }
            else
            {
                if(e.getClick().isRightClick())
                {
                    slot.getSlotListener().onRightClicked(p, this);
                }
                else if(e.getClick().isLeftClick())
                {
                    slot.getSlotListener().onLeftClicked(p, this);
                }
                else if(e.getClick().isShiftClick())
                {
                    slot.getSlotListener().onShiftClicked(p, this);
                }
            }
        }
    }



    @EventHandler
    public void onInvetoryClosed(final InventoryCloseEvent e)
    {
        if (e.getInventory() != inventory) return;
        if(!isClosable())
        {
            Bukkit.getScheduler().runTaskLater(EasyPluginCore.getInstance(), new Runnable() {
                @Override
                public void run() {
                    open(e.getPlayer());
                }
            }, 20);
            return;
        }
        else
        {
            if(onClosed != null) onClosed.run();
        }

        InventoryCloseEvent.getHandlerList().unregister(this);
        InventoryDragEvent.getHandlerList().unregister(this);
        InventoryClickEvent.getHandlerList().unregister(this);
    }

    @EventHandler
    public void onInventoryDrag(final InventoryDragEvent e) {
        if (e.getInventory() == inventory) {
            e.setCancelled(true);
        }
    }

    public void open(final HumanEntity humanEntity) {
        humanEntity.openInventory(inventory);
    }







}