package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.Main;

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

        nextCell.getActor().health -= usingMathClass(20);;
        cell.getActor().health -= usingMathClass(10);;
        if (nextCell.getActor().health <= 0){
            nextCell.setActor(null);
        }
        if (cell.getActor().health <= 0){
            cell.setActor(null);
            Main.isAlive = false;
        }
        System.out.println(nextCell.getActor().health);
    }


    static int usingMathClass(int range) {
        double randomDouble = Math.random();
        randomDouble = randomDouble * range + 1;
        int randomInt = (int) randomDouble;
        System.out.println("random: " + randomInt);
        return randomInt;
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
