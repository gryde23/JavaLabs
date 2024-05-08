package task2;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class CurvePanel extends JPanel {

    private final Curve curve;

    public CurvePanel() {
        double[] xValues = new double[360];
        double[] yValues = new double[360];
        for (int i = 0; i < 360; i++) {
            double x = i * Math.PI / 180;
            xValues[i] = x;
            yValues[i] = Math.sin(x);
            //System.out.println(x + " " + yValues[i]);
        }

        curve = new Curve(xValues, yValues);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Path2D path = new Path2D.Double();
        g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        double scaleX = getWidth() / (4 * Math.PI);
        double scaleY = getHeight() / 4;

        path.moveTo(curve.getXValues()[0] * scaleX + (double) getWidth() / 2,
                -curve.getYValues()[0] * scaleY + (double) getHeight() / 2);
        for (int i = 0; i < curve.getXValues().length; i++) {
            double x = curve.getXValues()[i];
            double y = curve.getYValues()[i];
            int drawX = (int) (x * scaleX + getWidth() / 2);
            int drawY = (int) (-y * scaleY + getHeight() / 2);
//      System.out.println(drawX + " " + drawY);
            path.lineTo(drawX, drawY);
        }

        g2d.draw(path);
    }
}

