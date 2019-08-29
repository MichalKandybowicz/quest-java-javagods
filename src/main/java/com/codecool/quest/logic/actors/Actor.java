package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 100;

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
        } else if (nextCell.getActor() != null){
            fight(nextCell);
        }
    }

    public void fight(Cell nextCell){
        nextCell.getActor().health -= 20;
        cell.getActor().health -= 20;
        if (nextCell.getActor().health <= 0){
            nextCell.setActor(null);
        }
        if (cell.getActor().health <= 0){
            cell.setActor(null);
        }
        System.out.println(nextCell.getActor().health);
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
