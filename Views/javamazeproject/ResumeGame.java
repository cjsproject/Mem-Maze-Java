package Views.javamazeproject;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Game.MazeGame;
import Game.Point;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author Cyrus
 */
public class ResumeGame extends GameUI {

    public ResumeGame(){
        super();
    }

    public static void resume() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("saveGame.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        MazeGame tempGame = (MazeGame)ois.readObject();
        game.board = tempGame.board;

        fis = new FileInputStream("savePoints.dat");
        ois = new ObjectInputStream(fis);
        generatedPoints = (ArrayList<Point>)ois.readObject();
        game.setBoardPoints(generatedPoints);
        // build UI, register event handlers, etc etc
    }
}
