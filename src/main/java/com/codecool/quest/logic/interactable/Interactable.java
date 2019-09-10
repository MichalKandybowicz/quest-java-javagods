package com.codecool.quest.logic.interactable;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.interfaces.Drawable;
import com.codecool.quest.logic.items.Item;

public abstract class Interactable implements Drawable {

    private Cell cell;

    public Interactable(Cell cell) { //Constructor, it makes items based on arguments
        this.cell = cell;
        this.cell.setInteractable(this);
    }

    public abstract void Use();
    public abstract boolean isPassable();
    public abstract boolean needsKey();

    public Item searchForItems(){
        return null;
    }
}