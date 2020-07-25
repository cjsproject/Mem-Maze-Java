package Game;

import javafx.scene.layout.Pane;

import java.io.Serializable;

public class Point extends Pane implements Serializable {
    private int x;
    private int y;
    private int step;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.step = 0;
    }

    public Point(int x, int y, int step) {
        this.x = x;
        this.y = y;
        this.step = step;
    }

    public boolean equals(Point point) {
        return x == point.getX() && y == point.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getStep() {
        return step;
    }

}
