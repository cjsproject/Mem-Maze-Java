package Views.javamazeproject;

import Game.Point;
import javafx.scene.layout.*;

import static Views.javamazeproject.EventListeners.gridEventListener;

public class GridWork extends GameUI{

    protected static void createGrid(){
        for (int i = 0; i < COLUMNS; i++){
            playerGrid.getColumnConstraints().add(new ColumnConstraints(40));
            playerGrid.getRowConstraints().add(new RowConstraints(40));

            AIGrid.getColumnConstraints().add(new ColumnConstraints(40));
            AIGrid.getRowConstraints().add(new RowConstraints(40));

            for (int j = 0; j < ROWS; j++){
                Pane playerPane = new Pane();
                Pane opponentPane = new Pane();
                for (Point point : generatedPoints) {
                    if (point.getX() == i && point.getY() == j) {
                        Timers.setAtom(point.getStep(), playerPane);
                        Timers.setAtom(point.getStep(), opponentPane);
                    }
                }
                Style(i, j, playerPane, opponentPane);
            }
        }
    }

    protected static void clearAllGrids(){

        playerGrid.getChildren().clear();
        AIGrid.getChildren().clear();

        for (int i = 0; i < COLUMNS; i++){

            for (int j = 0; j < ROWS; j++){
                Pane playerPane = new Pane();
                Pane opponentPane = new Pane();

                Style(i, j, playerPane, opponentPane);
            }
        }

        playerGrid.setBackground(Background.EMPTY);
        AIGrid.setBackground(Background.EMPTY);
        gridEventListener();
    }

    private static void Style(int i, int j, Pane playerPane, Pane opponentPane) {
        playerPane.getStyleClass().add("gridCSS-cell");
        opponentPane.getStyleClass().add("gridCSS-cell");

        if (i==0){
            playerPane.getStyleClass().add("first-column");
            opponentPane.getStyleClass().add("first-column");
        }
        if (j==0) {
            playerPane.getStyleClass().add("first-row");
            opponentPane.getStyleClass().add("first-row");
        }
        playerGrid.add(playerPane,i,j);
        AIGrid.add(opponentPane,i,j);
    }

    protected static void clearGrid(GridPane oGrid){

        oGrid.getChildren().clear();

        for (int i = 0; i < COLUMNS; i++){

            for (int j = 0; j < ROWS; j++){
                Pane opponentPane = new Pane();

                opponentPane.getStyleClass().add("gridCSS-cell");

                if (i==0){
                    opponentPane.getStyleClass().add("first-column");
                }
                if (j==0) {
                    opponentPane.getStyleClass().add("first-row");
                }
                oGrid.add(opponentPane,i,j);
            }
        }

        oGrid.setBackground(Background.EMPTY);
        gridEventListener();
    }

}
