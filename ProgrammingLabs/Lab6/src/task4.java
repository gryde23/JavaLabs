import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class task4 {
    private JFrame frame;
    private JPanel panel;
    public task4(){
        frame = new JFrame("Task 4");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        JLabel label = new JLabel("Нажмите мышью на панель");
        panel.add(label);
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                switch (c) {
                    case 'b' -> {
                        label.setForeground(Color.BLUE);
                        label.repaint();
                    }
                    case 'g' -> {
                        label.setForeground(Color.GREEN);
                        label.repaint();
                    }
                    case 'r' -> {
                        label.setForeground(Color.RED);
                        label.repaint();
                    }
                    case 'w' -> {
                        label.setForeground(Color.WHITE);
                        label.repaint();
                    }
                    case 'o' -> {
                        label.setForeground(Color.ORANGE);
                        label.repaint();
                    }
                }
            }
        });
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                label.setText("Координаты клика: X=" + x + ", Y=" + y);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }
}
