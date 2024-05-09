package task4;

import java.awt.geom.Path2D;

public class Grid {

    private int xSpacing;
    private int ySpacing;

    public Grid() {
        this.xSpacing = 50;
        this.ySpacing = 50;
    }

    public void setSpacing(int xSpacing, int ySpacing) {
        this.xSpacing = xSpacing;
        this.ySpacing = ySpacing;
    }

    protected void draw(Path2D path, int width, int height) {
        for (var i = 0; i <= width; i+=15) {
            path.moveTo(i, height / 2 + 2);
            path.lineTo(i, height / 2 - 2);
        }

        for (var i = 0; i <= height; i+=15) {
            path.moveTo(width/ 2 + 2, i);
            path.lineTo( width / 2 - 2, i);
        }

    }
}
