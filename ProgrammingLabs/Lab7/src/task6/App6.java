package task6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class App6 extends JFrame {

    public App6() {
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dice dice = new Dice(540);

        JButton backColor = new JButton("BackColor");
        JButton circlesColor = new JButton("Circles Color");
        JButton act = new JButton("Active/Non-active");
        backColor.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Выберите цвет", Color.RED);
            if (newColor != null) {
                dice.setBackColor(newColor);
                repaint();
            }
        });
        circlesColor.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Выберите цвет", Color.RED);
            if (newColor != null) {
                dice.setCirclesColor(newColor);
                repaint();
            }
        });
        act.addActionListener(e -> {
            dice.active = !dice.active;
        });
        JPanel btnpanel = new JPanel();
        btnpanel.add(backColor);
        btnpanel.add(circlesColor);
        btnpanel.add(act);


        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawRect(130, 130, 540, 540);
                g.setColor(dice.getBackColor());
                g.fillRect(131, 131, 538, 538);
                switch (dice.getPoints()) {
                    case 1 -> {
                        dice.drawCirle(g, 130 + 540 / 3, 130 + 540 / 3);
                    }
                    case 2 -> {
                        dice.drawCirle(g, 130, 130 + 540 / 3);
                        dice.drawCirle(g, 130 + 2 * 540 / 3, 130 + 540 / 3);
                    }
                    case 3 -> {
                        dice.drawCirle(g, 130 + 2 * 540 / 3, 130);
                        dice.drawCirle(g, 130 + 540 / 3, 130 + 540 / 3);
                        dice.drawCirle(g, 130, 130 + 2 * 540 / 3);
                    }
                    case 4 -> {
                        dice.drawCirle(g, 130, 130);
                        dice.drawCirle(g, 130 + 2 * 540 / 3, 130);
                        dice.drawCirle(g, 130, 130 + 2 * 540 / 3);
                        dice.drawCirle(g, 130 + 2 * 540 / 3, 130 + 2 * 540 / 3);
                    }
                    case 5 -> {
                        dice.drawCirle(g, 130 + 540 / 3, 130 + 540 / 3);
                        dice.drawCirle(g, 130, 130);
                        dice.drawCirle(g, 130 + 2 * 540 / 3, 130);
                        dice.drawCirle(g, 130, 130 + 2 * 540 / 3);
                        dice.drawCirle(g, 130 + 2 * 540 / 3, 130 + 2 * 540 / 3);
                    }
                    case 6 -> {
                        dice.drawCirle(g, 130, 130);
                        dice.drawCirle(g, 130, 130 + 540 / 3);
                        dice.drawCirle(g, 130, 130 + 2 * 540 / 3);
                        dice.drawCirle(g, 130 + 2 * 540 / 3, 130);
                        dice.drawCirle(g, 130 + 2 * 540 / 3, 130 + 540 / 3);
                        dice.drawCirle(g, 130 + 2 * 540 / 3, 130 + 2 * 540 / 3);
                    }
                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (dice.active){
                    Random random = new Random();
                    dice.setPoints(random.nextInt(1, 7));
                    panel.repaint();
                }
            }
        });
        panel.setLayout(new BorderLayout());
        panel.add(btnpanel, BorderLayout.NORTH);

        add(panel);
        setVisible(true);
    }

}
