package Player;

import Game.Enums.Direction;
import Game.MazeGame;
import Game.Point;

import static Views.javamazeproject.mainGUI.generatedPoints;

public abstract class AbsPlayer extends MazeGame {
    protected Point position;
    protected Direction direction;

    public AbsPlayer() {
        position = generatedPoints.get(0);
        System.out.println(position.getX() + ", " + position.getY());
    }

    public abstract void movePlayer(Direction d);
    public void resetPosition(){this.position = generatedPoints.get(0);}
    public Point getPosition(){ return position; }

}
