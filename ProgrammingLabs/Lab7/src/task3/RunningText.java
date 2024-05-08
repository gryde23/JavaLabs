package task3;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RunningText extends JPanel {
    protected String[] messages = {
            "Hello world!",
            "text",
            "string",
            "Java Swing",
            "abeba"
    };
    protected String currentMessage;
    protected int x;
    private Random random = new Random();

    public RunningText() {
        setPreferredSize(new Dimension(800, 100));
        setBackground(Color.WHITE);
//        setCurrentMessage(messages[random.nextInt(messages.length)]);

//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                setCurrentMessage(messages[random.nextInt(messages.length)]);
//            }
//        });

        Timer timer = new Timer(1000 / 60, e -> {
            x += 3;
            if (x > getWidth()) {
                x = -100;
            }
            repaint();
        });
        timer.start();
    }

    protected void setCurrentMessage(String message) {
        currentMessage = message;
        x = -100;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString(currentMessage, x, getHeight() / 2 + g.getFontMetrics().getHeight() / 2);
    }

}

