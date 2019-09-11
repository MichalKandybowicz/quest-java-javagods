package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.interfaces.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;

    public Item(Cell cell) {
        this.cell = cell;
        if (cell != null) this.cell.setItem(this);
    }

    public void vanishItem() {
        this.cell.setItem(null);
        this.cell = null;
    }
}


