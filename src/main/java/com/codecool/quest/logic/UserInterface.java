package com.codecool.quest.logic;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class UserInterface {

    private Label healthLabelText = new Label("Health: ");
    private Label healthLabel = new Label();
    private Label inventoryLabel = new Label();
    private Label messageLabel = new Label();

    public GridPane topPane = new GridPane();
    public GridPane bottomPane = new GridPane();
    public GridPane ui = new GridPane();


    public UserInterface(){

    }

    public Label getMessageLabel() {
        return messageLabel;
    }

    public Label getHealthLabel() {
        return healthLabel;
    }

    public Label getInventoryLabel() {
        return inventoryLabel;
    }

    public void CreateUserInterfaceSideBar(GameMap map){
        ui.getStyleClass().add("ui-pane");

        healthLabelText.setTextFill(Color.ROSYBROWN);
        healthLabel.setTextFill(Color.ROSYBROWN);
        ui.add(healthLabelText, 0, 4);
        ui.add(healthLabel, 1, 4);

        inventoryLabel.setTextFill(Color.ROSYBROWN);
        ui.add(inventoryLabel, 0, 7);
        Label lab = new Label("»»»»Inventory««««");
        lab.setTextFill(Color.ROSYBROWN);
        ui.add(lab, 0, 20);
    }

    public void showInventory(GameMap map){
        com.codecool.quest.logic.Inventory inv = map.getPlayer().getPlayerInventory();
        inventoryLabel.setText(inv.toString());
    }
}
