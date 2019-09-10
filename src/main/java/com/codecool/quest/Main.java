package com.codecool.quest;

import com.codecool.quest.logic.*;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.interactable.Interactable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);

    private GraphicsContext context = canvas.getGraphicsContext2D();

    private UserInterface UserInterface = new UserInterface();

    Label healthLabel = new Label();
    static public boolean isAlive = true;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        UserInterface.CreateUserInterfaceSideBar(map);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(UserInterface.ui);
        borderPane.setBottom(UserInterface.bottomPane);
        borderPane.setTop(UserInterface.topPane);

        UserInterface.showInventory(map);
        createScene(borderPane, primaryStage);
    }

    private void createScene(BorderPane borderPane, Stage primaryStage){
        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        Player player = map.getPlayer();
        if (isAlive){
            Inventory playerInventory = player.getPlayerInventory();
            Interactable interactableItem = player.getNextCell().getInteractable();
            switch (keyEvent.getCode()) {
                case UP:
                   player.move(0, -1);
                    player.changeDirection("up");
                    refresh();
                    break;

                case DOWN:
                    player.move(0, 1);
                    player.changeDirection("down");
                    refresh();
                    break;

                case LEFT:
                    player.move(-1, 0);
                    player.changeDirection("left");
                    refresh();
                    break;

                case RIGHT:
                    player.move(1,0);
                    player.changeDirection("right");
                    refresh();
                    break;
                case E:
                    if (player.pickItem()) {
                        UserInterface.getMessageLabel().setText(String.format("Picked a %s", playerInventory.getLastItem()));
                    } else if (interactableItem != null) { //check for doors/chests
                        String interactionMessage = player.interactWithObject(interactableItem);
                        UserInterface.getMessageLabel().setText(interactionMessage);
                    }
                    break;
            }
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (
                        ((map.getPlayer().getX() >= x-1 && map.getPlayer().getX() <= x+1) && (map.getPlayer().getY() >= y-3 && map.getPlayer().getY() <= y+3))
                                || (map.getPlayer().getX() >= x-3 && map.getPlayer().getX() <= x+3) && (map.getPlayer().getY() >= y-1 && map.getPlayer().getY() <= y+1)
                                || (map.getPlayer().getX() >= x-2 && map.getPlayer().getX() <= x+2) && (map.getPlayer().getY() >= y-2 && map.getPlayer().getY() <= y+2)
                ){
                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell.getActor(), x, y);
                    } else {
                        Tiles.drawTile(context, cell, x, y);
                    }
                }

            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());

        UserInterface.getHealthLabel().setText("" + map.getPlayer().getHealth());
        showInventory();
        if(map.getPlayer().getHealth() <= 0){
            UserInterface.getMessageLabel().setText("YOU ARE DEAD!!");
        }
    }

    private void showInventory(){
        Inventory inv = map.getPlayer().getPlayerInventory();
        UserInterface.getInventoryLabel().setText(inv.toString());
    }
}
