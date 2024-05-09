package task4;

import java.awt.geom.Path2D;

public class Curve {
    private double[] xValues;
    private double[] yValues;

    public Curve(double[] xValues, double[] yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
    }

    public void setData(double[] xValues, double[] yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
    }

    public double[] getXValues() {
        return xValues;
    }

    public double[] getYValues() {
        return yValues;
    }

    public void draw(Path2D path, double scaleX, double scaleY, int width, int height) {
        height += 3;
        path.moveTo(getXValues()[0] * scaleX + (double) width / 2,
                -getYValues()[0] * scaleY + (double) height / 2);
        for (int i = 0; i < getXValues().length; i++) {
            double x = getXValues()[i];
            double y = getYValues()[i];
            int drawX = (int) (x * scaleX + width / 2);
            int drawY = (int) (-y * scaleY + height / 2);
            path.lineTo(drawX, drawY);
        }
    }
}
