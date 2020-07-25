package Game.GameTypes;

import Game.Interfaces.Checks;
import Game.Point;

import java.util.Arrays;

public class GridGame implements Checks {

    public String[][] board;
    //private final int MAX_BOARD_SIZE = 15;

    public GridGame() {
        this.board = new String[6][6];
        for (String[] strings : board) {
            Arrays.fill(strings, "-");
        }
    }

    public void printBoard() {
        for (String[] strings : board) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }


    @Override
    public boolean isValidMovement(Point givenPoint) {
        int x = givenPoint.getX();
        int y = givenPoint.getY();
        return y < 0 || y > board.length-1 || x < 0 || x > board.length-1;
    }

    @Override
    public boolean isWinner() {
        return false;
    }
}