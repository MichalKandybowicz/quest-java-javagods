package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
import sun.awt.geom.AreaOp;
import sun.util.locale.provider.SPILocaleProviderAdapter;

import javax.sql.rowset.spi.SyncProvider;
import javax.swing.*;
import java.net.SocketOption;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (!nextCell.getType().toString().equals("WALL") &&
                nextCell.getActor() == null){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    public int getHealth() {
        return health;
    }



    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
