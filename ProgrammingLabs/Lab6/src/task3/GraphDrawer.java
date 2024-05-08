package task3;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class GraphDrawer extends JFrame {
    private Graph graph;

    public GraphDrawer(Graph graph) {
        this.graph = graph;
    }
    public void draw() {
        setTitle("График функции");
        setSize(600, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(graph);
        setVisible(true);
    }
}
