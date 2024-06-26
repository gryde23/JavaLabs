import javax.swing.*;
import java.awt.*;

public class task1 {
    private JFrame frame;
    private JPanel panel;

    public task1() {
        frame = new JFrame("Task 1");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D)g;
                g2.setStroke(new BasicStroke(2));
                g.setColor(Color.BLACK);
                g.drawLine(150, 300, 150, 0);
                g.drawLine(0, 150, 300, 150);
                Polygon p = new Polygon();
                for (double x = -Math.PI; x <= Math.PI; x+= 0.01){
                    double y = Math.sin(x);
                    int xPos = (int) ((x + Math.PI) * 300 / (2 * Math.PI));
                    int yPos = (int) ((300 / 2) - (y * 300 / 2));
                    p.addPoint(xPos, yPos);
                }
                g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
            }
        };
        frame.add(panel);
        frame.setVisible(true);
    }
}