package task4;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class Graph extends JPanel {

    protected final ArrayList<Curve> curves;
    Axis axis;
    Grid grid;
    private Color graphColor = Color.BLACK;
    public Graph() {
        this.curves = new ArrayList<Curve>();
        this.grid = new Grid();
    }

    public void addCurve(Curve curve) {
        this.curves.add(curve);
    }
    public void removeLastCurve(){
        this.curves.removeLast();
    }
    public void setGraphColor(Color newColor) {
        this.graphColor = newColor;
        // Обновление графика с новым цветом
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(this.graphColor);
        Graphics2D g2d = (Graphics2D) g;
        Path2D path = new Path2D.Double();

        double scaleX = getWidth() / (4 * Math.PI);
        double scaleY = getHeight() / 4;
        axis = new Axis(getWidth(), getHeight());
        for (var c : curves) {
            c.draw(path, scaleX, scaleY, getWidth(), getHeight());
        }
        axis.draw(path);
        grid = new Grid();
        grid.draw(path, getWidth(), getHeight());
        g2d.draw(path);
    }


}
