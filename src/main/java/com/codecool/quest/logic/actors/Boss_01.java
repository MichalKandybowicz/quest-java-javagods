package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Boss_01 extends Actor {
    public Boss_01(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "boss_01";
    }
}