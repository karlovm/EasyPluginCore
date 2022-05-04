package ru.etysoft.epcore.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class GUIPageable {


    private List<Slot> slots;
    private List<Slot> bottomBar;
    private Slot lastProcessed;
    private Slot lastFirstProcessed;
    private GUITable guiTable;
    private  HashMap<Integer, Slot> matrix;


    public GUIPageable()
    {

    }

    public void initialize(List<Slot> slots, List<Slot> bottomBar, GUITable guiTable, Player player) throws Exception {
        matrix = new HashMap<>();
        this.slots = slots;
        if (bottomBar.size() > 9) {
            throw new Exception("Bottom bar max size is 9");
        }
        this.guiTable = guiTable;


        this.bottomBar = bottomBar;
        if (guiTable.lines != 6)
        {
            throw new Exception("Pageable GUI requires 6 lines");
        }

        guiTable.setMatrix(matrix);
        guiTable.open(player);
    }

    public void nextPage()
    {
        if(!hasNextPage()) return;
        matrix.clear();
        int i = 1;

        int startIndex = 0;

        if(lastProcessed != null)
        {
            startIndex = slots.indexOf(lastProcessed) + 1;
        }

        processSlots(i, startIndex);
    }

    private void processBottomBar()
    {
        int slotIndex = 46;
        for(Slot slot : bottomBar)
        {
            matrix.put(slotIndex, slot);
            slotIndex++;
        }

    }

    public void prevPage()
    {
        if(!hasPrevPage()) return;
        matrix.clear();
        int i = 1;

        int startIndex = 0;

        if(lastProcessed != null)
        {
            startIndex = slots.indexOf(lastFirstProcessed) - 45;
        }

        processSlots(i, startIndex);
    }

    private void processSlots(int i, int startIndex) {
        for(Slot slot : slots)
        {
            if(slots.indexOf(slot) >= startIndex) {
                if(i == 1)
                {
                    lastFirstProcessed = slot;
                }
                matrix.put(i, slot);


                if (i >= 45) {
                    lastProcessed = slot;
                    break;
                }
                i++;
            }
        }
        processBottomBar();
        guiTable.setMatrix(matrix);
    }


    public boolean hasNextPage()
    {
        if(slots.size() == 0) return false;
        if(lastProcessed == null) return true;
        return slots.indexOf(lastProcessed) != slots.size() - 1;
    }

    public boolean hasPrevPage()
    {
        if(slots.size() == 0) return false;
        if(lastProcessed == null) return false;
        return slots.indexOf(lastFirstProcessed) > 0;
    }




}
