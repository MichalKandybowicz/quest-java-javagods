package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Axe extends Item {

    public Axe(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "axe";
    }

    @Override
    public String toString() {
        return "AXE";
    }
}
