package task6;

import java.awt.*;

public class Dice {
    private Color circlesColor = Color.BLACK;
    private int points = 1;
    private Color backColor = Color.WHITE;
    private int size;
    protected boolean active = true;

    public Dice(int size) {
        this.size = size;
    }
    public void setBackColor(Color backColor) {
        this.backColor = backColor;
    }

    public int getPoints() {
        return points;
    }

    public Color getBackColor() {
        return backColor;
    }

    public Color getCirclesColor() {
        return circlesColor;
    }

    public void setCirclesColor(Color circlesColor) {
        this.circlesColor = circlesColor;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void drawCirle(Graphics g, int x, int y){
        g.setColor(circlesColor);
        g.fillOval(x + size / 9, y + size / 9, size / 9, size / 9);
    }


}
