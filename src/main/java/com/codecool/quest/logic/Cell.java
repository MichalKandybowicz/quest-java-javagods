package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.interactable.Interactable;
import com.codecool.quest.logic.interfaces.Drawable;
import com.codecool.quest.logic.items.Item;

public class Cell implements Drawable {
    private Item item;
    private CellType type;
    private Actor actor;
    private GameMap gameMap;
    private com.codecool.quest.logic.interactable.Interactable interactable;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setItem(Item item){
        this.item = item;
    }

    public Item getItem(){
        return item;
    }

    public void setInteractable(com.codecool.quest.logic.interactable.Interactable interactable) {
        this.interactable = interactable;
    }

    public Interactable getInteractable(){return interactable;}

}
