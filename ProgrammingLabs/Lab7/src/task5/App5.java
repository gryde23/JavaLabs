package task5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class App5 extends JFrame {

    public App5() {
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel upperLabel = new JLabel("dialog");
        JLabel downLabel = new JLabel("dialog");
        JLabel centerLabel = new JLabel("dialog");

        // Настройка шрифта для меток
        Font upperFont = new Font("dialog", Font.BOLD | Font.ITALIC, 10);
        Font downFont = new Font("dialog", Font.BOLD | Font.ITALIC, 10);
        Font centerFont = new Font("dialog", Font.BOLD, 18);

        // Применяем шрифты к меткам
        upperLabel.setFont(upperFont);
        upperLabel.setHorizontalAlignment(SwingConstants.LEFT);
        downLabel.setFont(downFont);
        downLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        centerLabel.setFont(centerFont);
        centerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel Lpanel = new JPanel();
        Lpanel.setLayout(new BorderLayout());
        Lpanel.setPreferredSize(new Dimension(300, 300));

        Lpanel.add(upperLabel, BorderLayout.NORTH);
        Lpanel.add(centerLabel, BorderLayout.CENTER);
        Lpanel.add(downLabel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        JButton upColor = new JButton("UpColor");
        JButton downColor = new JButton("DownColor");
        JButton centerColor = new JButton("CenterColor");
        JButton backColor = new JButton("BackColor");
        upColor.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Выберите цвет", Color.RED);
            if (newColor != null) {
                upperLabel.setForeground(newColor);
            }
        });
        downColor.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Выберите цвет", Color.RED);
            if (newColor != null) {
                downLabel.setForeground(newColor);
            }
        });
        centerColor.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Выберите цвет", Color.RED);
            if (newColor != null) {
                centerLabel.setForeground(newColor);
            }
        });

        buttonPanel.add(upColor);
        buttonPanel.add(downColor);
        buttonPanel.add(centerColor);
        buttonPanel.add(backColor);

        JPanel panel = new JPanel();
        backColor.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Выберите цвет", Color.RED);
            if (newColor != null) {
                Lpanel.setBackground(newColor);
                Lpanel.repaint();
            }
        });
        panel.setLayout(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(Lpanel);

        add(panel);
        setVisible(true);
    }
}
