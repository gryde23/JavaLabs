package task4;

import javax.swing.*;

import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class App4 extends Graph {
    public App4() {
        super();
        JFrame frame = new JFrame("Task4");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JComboBox<Object> graphComboBox = new JComboBox<>(new String[]{"sin(x)", "sin(x*x) + cos(x*x)", "2*sin(x)+cos(2*x)"});
        JTextField a = new JTextField(5);
        JTextField b = new JTextField(5);
        JButton set = new JButton("Set");
        JButton colorChoose = new JButton("ChooseColor");
        set.addActionListener(e -> {
            double[] xValues = new double[360];
            double[] yValues = new double[360];
            for (int i = Integer.parseInt(a.getText()); i < Integer.parseInt(b.getText()); i++) {
                if (i < 0) {
                    double x = i * Math.PI / 180;
                    int c = Integer.parseInt(a.getText());
                    xValues[i + Math.abs(c)] = x;
                    if (graphComboBox.getSelectedIndex() == 0) yValues[i + Math.abs(c)] = Math.sin(x);
                    else if (graphComboBox.getSelectedIndex() == 1) {
                        yValues[i + Math.abs(c)] = Math.sin(x * x) + Math.cos(x * x);
                    } else yValues[i + Math.abs(c)] = 2 * Math.sin(x) + Math.cos(2 * x);
                    continue;
                }
                double x = i * Math.PI / 180;
                xValues[i] = x;
                if (graphComboBox.getSelectedIndex() == 0) yValues[i] = Math.sin(x);
                else if (graphComboBox.getSelectedIndex() == 1) {
                    yValues[i] = Math.sin(x * x) + Math.cos(x * x);
                } else yValues[i] = 2 * Math.sin(x) + Math.cos(2 * x);
            }
            Curve curve = new Curve(xValues, yValues);
            if (!this.curves.isEmpty()) this.removeLastCurve();
            this.addCurve(curve);
            repaint();
        });

        colorChoose.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(frame, "Выберите цвет для графика", Color.RED);
            if (newColor != null) {
                // Изменяем цвет графика на выбранный цвет
                this.setGraphColor(newColor);
                // Обновляем график, чтобы отразить изменение цвета
                repaint();
            }
        });


        add(graphComboBox);
        add(a);
        add(b);
        add(set);
        add(colorChoose);

        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
