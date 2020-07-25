package Views.javamazeproject;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Game.MazeGame;
import Game.Point;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Cyrus
 */
public class mainGUI extends Application {

    protected static final int ROWS = 6;
    protected static final int COLUMNS = 6;
    public static MazeGame game = new MazeGame();
    public static ArrayList<Point> generatedPoints;

    @Override
    public void start(Stage theStage) {

        final double CANVAS_WIDTH = 550;
        final double CANVAS_HEIGHT = 700;

        final Image titleScreenImg = new Image( "Views/picpack/meh.jpg" ); //title screen image
        final Image resumeGameImg = new Image("Views/picpack/ResumeGame.PNG"); //the resume button image
        final Image newGameImg = new Image("Views/picpack/NewGame.PNG"); //the new game button image

        final ImageView bgImgView = new ImageView();
        bgImgView.setImage(titleScreenImg); //set the image of the title screen

        bgImgView.setFitWidth(550);
        bgImgView.setFitHeight(700);

        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        Text titleFont = new Text();
        titleFont.setEffect(ds);
        titleFont.setCache(true);
        titleFont.setX(10.0f);
        titleFont.setY(270.0f);
        titleFont.setFill(Color.YELLOW);
        titleFont.setText("The Memory Maze");
        titleFont.setFont(Font.font(null, FontWeight.BOLD, 32));


        final Button resumeBtn  = new Button();
        final ImageView resumeBtnNode = new ImageView();
        resumeBtnNode.setFitHeight(69);
        resumeBtnNode.setFitWidth(142);
        resumeBtn.setOnAction(e -> {
            try {
                ResumeGame.resume();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            theStage.setScene(new GameUI().mainScene);
        });

        final Button newBtn = new Button();
        final ImageView newBtnNode = new ImageView();
        newBtnNode.setFitHeight(69);
        newBtnNode.setFitWidth(142);
        newBtn.setOnAction(e -> theStage.setScene(new GameUI().mainScene));


        resumeBtnNode.setImage(resumeGameImg); //set the image of the resume button
        newBtnNode.setImage(newGameImg); //set the image of the new game button

        resumeBtn.setGraphic(resumeBtnNode);
        resumeBtn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))); //this is to make the button background transparent

        newBtn.setGraphic(newBtnNode);
        newBtn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        /*
         * create the container of  buttons
         */
        final VBox buttonContainer = new VBox(1);
        buttonContainer.setAlignment(Pos.CENTER);
        Insets buttonContainerPadding = new Insets(220, 1, 1, 1);
        buttonContainer.setPadding(buttonContainerPadding);
        buttonContainer.getChildren().addAll(resumeBtn,newBtn);

        theStage.setTitle("CyLuRok Maze Game!!");
        theStage.getIcons().add(titleScreenImg); //stage icon

        StackPane root = new StackPane();
        StackPane.setAlignment(titleFont, Pos.CENTER);

        root.getChildren().addAll(bgImgView,titleFont,buttonContainer); //add the title screen and button container to the stackpane
        Scene theScene = new Scene( root, Color.CHOCOLATE );
        theStage.setScene( theScene );
        theStage.setWidth(CANVAS_WIDTH);
        theStage.setHeight(CANVAS_HEIGHT);
        //mainStage = theStage;
        theStage.show();

    }

    public void startProject(String[] args) {
        launch(args);
    }

}