package task3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BallsApp extends JPanel {
    protected List<Ball> balls = new ArrayList<>();
    protected Timer timer;
    protected MouseAdapter ma;

    public BallsApp() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        ma = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Random random = new Random();
                int x = random.nextInt(getWidth() - 1);
                int y = random.nextInt(getHeight() - 1);
                Color color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
                balls.add(new Ball(x, y, 20, color));
            }
        };

        timer = new Timer(1000 / 60, e -> {
            for (Ball ball : balls) {
                ball.move(getWidth(), getHeight());
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball ball : balls) {
            ball.draw(g);
        }
    }

}
