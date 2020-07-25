package Game;

import Game.Enums.Direction;
import Game.GameTypes.GridGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class MazeGame extends GridGame implements Serializable {

    public MazeGame() {
        start();
    }

    //private Difficulty difficulty;
    private ArrayList<Point> boardPoints = new ArrayList<>();

    private int playerTileCount = 0;

    public void start() {
        generateBoard();
        //super.printBoard();
    }

    private void generateBoard() {

        Random rand = new Random();
        int tileCount = 1;
        int y = 0;
        int prevX = rand.nextInt(board.length - 1);
        Direction[] directionChoices = {
                Direction.DOWN,
                Direction.LEFT,
                Direction.RIGHT
        };
        boardPoints.add(new Point(prevX,y, tileCount));
        board[y][prevX] = tileCount++ + "";
        while (y < board.length) {
            boolean directionFound = false;
            while (!directionFound) {
                Direction randomDirection = directionChoices[rand.nextInt(directionChoices.length)];
                if (randomDirection == Direction.LEFT && prevX - 1 >= 0 && board[y][prevX - 1].equals("-")) {
                    board[y][prevX - 1] = tileCount + "";
                    boardPoints.add(new Point(prevX-1,y, tileCount));
                    prevX -= 1;
                    tileCount++;
                    directionFound = true;
                } else if (randomDirection == Direction.RIGHT && prevX + 1 < board.length && board[y][prevX + 1].equals("-")) {
                    board[y][prevX + 1] = tileCount + "";
                    boardPoints.add(new Point(prevX+1,y, tileCount));
                    prevX += 1;
                    tileCount++;
                    directionFound = true;
                } else if (randomDirection == Direction.DOWN) {
                    if (y + 1 >= board.length) {
                        y++;
                        break;
                    }
                    board[++y][prevX] = tileCount + "";
                    boardPoints.add(new Point(prevX,y, tileCount));
                    tileCount++;
                    directionFound = true;
                }
                if (y + 1 >= board.length) {
                    y++;
                    break;
                }
            }
        }
    }

    public ArrayList<Point> getBoardPoints() {
        return boardPoints;
    }

    public void setBoardPoints(ArrayList<Point> x){
        boardPoints = x;
    }

    public int getPlayerTileCount() {
        return playerTileCount;
    }

    public void setPlayerTileCount(int playerTileCount) {
        this.playerTileCount = playerTileCount;
    }


/*    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }*/
}
