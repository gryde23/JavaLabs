package task3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class App3 extends BallsApp{
    private RunningText runningText = new RunningText();
    private int maxBalls;

    public App3(){
        super();
        JFrame frame = new JFrame("Task3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        JButton stop = new JButton("Stop");
        stop.addActionListener(e -> {
            timer.stop();
        });

        JButton resume = new JButton("Resume");
        resume.addActionListener(e -> {
            timer.start();
        });

        JTextField max = new JTextField(20);
        JButton set = new JButton("Set");
        set.addActionListener(e ->{
            if (!max.getText().isEmpty()) {
                maxBalls = Integer.parseInt(max.getText());
                runningText.setCurrentMessage("Кол-во шариков: " + balls.size() + ". Макс. шариков: " + maxBalls);
                runningText.repaint();
            }
        });

        runningText.setCurrentMessage("Кол-во шариков: " + balls.size() + ". Макс. шариков: " + maxBalls);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Создаем шарик при клике мыши
                Random random = new Random();
                int x = random.nextInt(getWidth() - 1);
                int y = random.nextInt(getHeight() - 1);
                Color color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
                if (balls.size() + 1 <= maxBalls) {
                    balls.add(new Ball(x, y, 20, color));
                    runningText.setCurrentMessage("Кол-во шариков: " + balls.size() + ". Макс. шариков: " + maxBalls);
                    runningText.repaint();
                }
            }
        });
        JPanel panel = new JPanel();
        panel.add(stop);
        panel.add(resume);
        panel.add(max);
        panel.add(set);
        frame.add(this);
        frame.add(runningText, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.NORTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
