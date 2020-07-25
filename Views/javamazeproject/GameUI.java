/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.javamazeproject;

import Game.Point;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static Views.javamazeproject.EventListeners.*;
import static Views.javamazeproject.GridWork.*;


/**
 *
 * @author Cyrus
 */
public class GameUI extends mainGUI {

    public static Scene mainScene;
    public static GridPane playerGrid = new GridPane();
    public static GridPane AIGrid = new GridPane();
    public static VBox mainContainer = new VBox();



    public GameUI() {

        generatedPoints = game.getBoardPoints();
        game.printBoard();

        Scene gameScene = new Scene(mainContainer, (COLUMNS * 40) + 100, (ROWS * 40) + 100);
        gameScene.getStylesheets().add("Views/picpack/gridCSS.css");

        playerGrid.getStyleClass().add("gridCSS");

        AIGrid.getStyleClass().add("gridCSS");

        final Menu menu1 = new Menu("Options");
        MenuItem saveOp = new MenuItem("Save and Quit");
        MenuItem quitOp = new MenuItem("Close");
        menu1.getItems().addAll(saveOp, quitOp);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1);

        //close option exits window, save writes MazeGame and generatedPoints to a .dat
        quitOp.setOnAction(e -> Platform.exit());
        saveOp.setOnAction(e -> {
            try {
                saveHandler();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Text playerText = new Text("Player Game Board");
        Text AIText = new Text("Opponent Game Board");
        Text timerText = new Text();

        decorateText(timerText);
        decorateText(playerText);
        decorateText(AIText);

        createGrid();

        gridEventListener(); // Add event listeners for game functionality

        playerGrid.setAlignment(Pos.CENTER);
        AIGrid.setAlignment(Pos.CENTER);
        mainContainer.setAlignment(Pos.CENTER);

        mainContainer.getChildren().addAll(menuBar, playerText, playerGrid, timerText, AIText, AIGrid);

        mainScene = gameScene;

        Timers.setTimerCountdown(timerText);

    }

    private void decorateText(Text text){
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        text.setEffect(ds);
        text.setCache(true);
        text.setX(10.0f);
        text.setY(270.0f);
        text.setFill(Color.RED);
        text.setFont(Font.font(null, FontWeight.BOLD, 18));
    }

    public static class Timers {

        private static final Integer STARTTIME = 5;
        protected static Timeline timeline;
        private static Integer timeSeconds = STARTTIME;
        private static final Integer AIstep = 1;
        public static Integer AIstepCount = AIstep;

        public static void setAtom(final Integer number, Pane pane){
            Circle circle = new Circle (20, 20f, 7);
            circle.setFill(Color.LIMEGREEN);
            circle.setRadius(15);

            Text step = new Text();
            step.setText(number.toString());
            step.setTextAlignment(TextAlignment.CENTER);
            step.setX(15);
            step.setY(25);

            Group group = new Group();
            group.getChildren().addAll(circle, step);
            pane.getChildren().add(group);
        }

        public static void setTimerCountdown(Text timeElapsed) {

            if (timeline != null) {
                timeline.stop();
            }
            timeSeconds = STARTTIME;

            // update timerLabel
            timeElapsed.setText(timeSeconds.toString());
            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(1), e -> {
                        timeSeconds--;
                        // update timerLabel
                        timeElapsed.setText(
                                "Time Until Path Disappears: "+timeSeconds.toString());
                        System.out.println(timeSeconds);
                        if (timeSeconds <= 0) {
                            timeline.stop();
                            clearAllGrids();
                            setTimerCountdownForAI();
                        }
                    }));
            timeline.playFromStart();
            timeElapsed.setText(" ");
        }

        public static void setTimerCountdownForAI() {

            if (timeline != null) {
                timeline.stop();
            }

            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);

            ArrayList<Point> AIGuesses = generatedPoints;

            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(new Random().nextInt(3)+1), e -> {
                        Point AIGuessPoint = AIGuesses.get(AIstepCount-1);

                        if (AIGuessPoint.getStep() == generatedPoints.get(generatedPoints.size()-1).getStep()){
                            timeline.stop();
                            mainContainer.getChildren().clear();
                            Button exit = new Button();
                            exit.setPadding(new Insets(50));
                            exit.setText("Exit");
                            exit.setOnMouseClicked(event -> Platform.exit());
                            mainContainer.getChildren().addAll(new Text("AI WINS"), exit);
                        }
                        if (AIGuesses.size() >= AIstepCount) {
                            Timers.setAtom(AIstepCount, (Pane) AIGrid.getChildren().get(AIGuessPoint.getX()*ROWS + AIGuessPoint.getY()));
                            AIstepCount++;
                        } else {
                            clearGrid(AIGrid);
                        }

                    }));
            timeline.playFromStart();
        }
    }

}