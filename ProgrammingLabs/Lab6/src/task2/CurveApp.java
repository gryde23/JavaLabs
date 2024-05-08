package task2;

import javax.swing.JFrame;

public class CurveApp extends JFrame {
    public CurveApp() {
        setTitle("График функции sin(x)");
        setSize(315, 337);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new CurvePanel());
        setVisible(true);
    }

}
