package Views.javamazeproject;

import Game.Point;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static Views.javamazeproject.GameUI.playerGrid;
import static Views.javamazeproject.GameUI.mainContainer;
import static Views.javamazeproject.GridWork.clearGrid;
import static Views.javamazeproject.mainGUI.game;
import static Views.javamazeproject.mainGUI.ROWS;


public class EventListeners {

    public static void gridEventListener() {
        for (Node element : playerGrid.getChildren()) {
            element.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                System.out.println("Row: " + GridPane.getRowIndex(element));
                System.out.println("Column: " + GridPane.getColumnIndex(element));

                int y = GridPane.getRowIndex(element);
                int x = GridPane.getColumnIndex(element);

                Point point = game.getBoardPoints().get(game.getPlayerTileCount());
                Point lastPoint = game.getBoardPoints().get(game.getBoardPoints().size()-1);

                if(point.getX() == x && point.getY() == y) {
                    System.out.println("This is the correct tile.");
                    game.setPlayerTileCount(game.getPlayerTileCount()+1);
                    System.out.println(game.getPlayerTileCount());
                    /*
                       After the setPlayerTileCount we will have to update the UI, and place the circles with numbers on that point.
                     */
                    GameUI.Timers.setAtom(game.getPlayerTileCount(), (Pane)playerGrid.getChildren().get(point.getX()*ROWS + point.getY()));

                    if(lastPoint.equals(new Point(x,y))) {
                        GameUI.Timers.timeline.stop();
                        mainContainer.getChildren().clear();
                        Button exit = new Button();
                        exit.setOnMouseClicked(event ->
                        {
                            Platform.exit();
                        });
                        exit.setText("Exit");
                        mainContainer.getChildren().addAll(new Text("YOU WON, DAWG..."), exit);
                    }
                } else {
                    System.out.println("This is not the correct tile.");
                    game.setPlayerTileCount(0);
                    /*
                        here we will have to reset the grid just to have empty panes.
                     */
                    clearGrid(playerGrid);
                }


            });
        }
    }

    public static void saveHandler() throws IOException {
        FileOutputStream fos = new FileOutputStream("saveGame.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(game);

        fos = new FileOutputStream("savePoints.dat");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(game.getBoardPoints());

        oos.close();
        fos.close();
        Platform.exit();
    }
}
