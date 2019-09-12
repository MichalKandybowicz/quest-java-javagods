package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Inventory;
import com.codecool.quest.logic.interactable.Interactable;
import com.codecool.quest.logic.items.Item;

public class Player extends Actor {
    private Inventory playerInventory;

    public Player(Cell cell) {
        super(cell);
        playerInventory = new Inventory();
        this.baseDMG = 25;
    }

    public Inventory getPlayerInventory(){
        return playerInventory;
    }

    public String getTileName() {
        return "player";
    }
    public boolean pickItem(){
        Cell cell = this.getCell();
        if(cell.getItem() != null){
            Item item = cell.getItem();
            item.vanishItem();
            playerInventory.addItem(item);
            return true;
        }
        return false;
    }

    public String interactWithObject(Interactable interactableItem) {
        if(interactableItem.needsKey()) {
            if (playerInventory.checkForItem("key")) {
                interactableItem.Use();
                playerInventory.removeItem("key");
                return "";
            }
        }else{
            interactableItem.Use();
            Item found = interactableItem.searchForItems();
            if(found != null) {
                playerInventory.addItem(found);
                return "Found a " + found;
            }else{
                return "Found nothing.";
            }
        }

        return "";
    }
}
