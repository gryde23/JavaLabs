import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class RunningText extends JPanel {
    private String[] messages = {
            "Hello world!",
            "text",
            "string",
            "Java Swing",
            "abeba"
    };
    private String currentMessage;
    private int x;
    private Random random = new Random();

    public RunningText() {
        setPreferredSize(new Dimension(800, 100));
        setBackground(Color.WHITE);
        setCurrentMessage(messages[random.nextInt(messages.length)]);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setCurrentMessage(messages[random.nextInt(messages.length)]);
            }
        });

        Timer timer = new Timer(1000 / 60, e -> {
            x += 3;
            if (x > getWidth()) {
                x = -100;
            }
            repaint();
        });
        timer.start();
    }

    private void setCurrentMessage(String message) {
        currentMessage = message;
        x = -100;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString(currentMessage, x, getHeight() / 2 + g.getFontMetrics().getHeight() / 2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Бегущая строка");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new RunningText());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

