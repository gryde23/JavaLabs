import javax.swing.*;
import java.awt.*;

public class Ball {
    private int x, y, radius;
    private int dx, dy;
    private Color color;

    public Ball(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;

        this.dx = (int) (Math.random() * 20 - 5); // Случайное значение скорости
        this.dy = (int) (Math.random() * 20 - 5); // Случайное значение скорости
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public void move(int width, int height) {
        x += dx;
        y += dy;

        // Отражение от стен
        if (x < 0 || x > width - radius) {
            dx = -dx;
        }
        if (y < 0 || y > height - radius) {
            dy = -dy;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x - radius, y - radius, radius * 2, radius * 2);
    }
}
