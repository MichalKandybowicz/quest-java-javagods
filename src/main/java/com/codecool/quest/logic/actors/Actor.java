package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.interfaces.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 100;
    private String direction;
    protected int baseDMG = 10;
    protected int DMGmod = 0;

    public Actor(Cell cell) {
        this.cell = cell;
        this.direction = "up";
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        if (isMoveValid(nextCell)) {
            changeCell(nextCell);
        } else if (nextCell.getActor() != null){
            attack(nextCell);
        }
    }

    public void attack(Cell cellToAttack){
        cellToAttack.getActor().receiveAttack(getDMG());
    }

    public void receiveAttack(int dmgReceived){
        this.changeHealth(dmgReceived);
        if (this.health<=0){
            DIE();
        }
    }

    protected boolean isMoveValid(Cell nextCell) {
        boolean isNextCellWall = nextCell.getType().equals(CellType.WALL);
        boolean isNextCellActor = nextCell.getActor() != null;

        return !isNextCellWall && !isNextCellActor;
    }

    public Cell getNextCell(){
        switch(direction){
            case "up":
                return cell.getNeighbor(0,1);
            case "down":
                return cell.getNeighbor(0, -1);
            case "left":
                return cell.getNeighbor(-1, 0);
            case "right":
                return cell.getNeighbor(1, 0);
            default:
                return cell;
        }
    }

    public int getHealth() {
        return health;
    }

    public void changeHealth(int changeOfHealth){
        health-=changeOfHealth;
    }

    public String getDirection(){
        return direction;
    }

    public void changeDirection(String newDirection){
        direction = newDirection;
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

    protected void changeCell(Cell nextCell) {
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public int getDMG(){
        return baseDMG+DMGmod;
    }

    private void DIE(){
        cell.setActor(null);
    }
}
