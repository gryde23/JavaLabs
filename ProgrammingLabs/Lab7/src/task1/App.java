package task1;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.util.stream.Stream;

public class App extends JFrame {
    public App(){
        setTitle("Task 1");
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        LookAndFeelInfo[] LAF = UIManager.getInstalledLookAndFeels();
        JComboBox<Object> comboBox = new JComboBox<>(Stream.of(LAF).map(
                LookAndFeelInfo::getName).toArray(String[]::new));
        comboBox.setMaximumSize(new Dimension(300, 50));
        panel.add(comboBox);

        JLabel label = new JLabel("New JLabel");
        panel.add(label);

        JButton button = new JButton("New JButton");
        panel.add(button);

        JCheckBox checkBox = new JCheckBox("New JCheckBox");
        panel.add(checkBox);

        JRadioButton radioButton1 = new JRadioButton("radioButton1");
        JRadioButton radioButton2 = new JRadioButton("radioButton2");
        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        panel.add(radioButton1);
        panel.add(radioButton2);

        JTable table;
        String[] columnNames = {"Язык", "Автор", "Год"};
        Object[][] data = {
                {"Си", "Деннис Ритчи", 1972},
                {"C++", "Бьерн Страуструп", 1983},
                {"Python", "Гвидо ван Россум", 1991},
                {"Java", "Джеймс Гослинг", 1995},
                {"JavaScript", "Брендон Айк", 1995},
                {"C#", "Андерс Хейлсберг", 2001},
                {"Scala", "Мартин Одерски", 2003}
        };
        table = new JTable(data, columnNames);
        panel.add(table);

        comboBox.addActionListener(e -> {
            String lafName = (String) comboBox.getSelectedItem();
            try {
                for (LookAndFeelInfo laf : LAF) {
                    if (laf.getName().equals(lafName)) {
                        UIManager.setLookAndFeel(laf.getClassName());
                        SwingUtilities.updateComponentTreeUI(App.this);
                        break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        add(panel);
        setVisible(true);
    }
}
