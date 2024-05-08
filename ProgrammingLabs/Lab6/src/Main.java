import task2.CurveApp;
import task3.GraphDrawer;
import task2.Curve;
import task3.Graph;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        new task1();
        CurveApp task2 = new CurveApp();
        task3();
        new task4();
        new task5();
    }

    public static void task3() {
        double[] xValues = new double[360];
        double[] yValues = new double[360];
        for (int i = 0; i < 360; i++) {
            double x = i * Math.PI / 180;
            xValues[i] = x;
            yValues[i] = Math.sin(x);
        }

        double[] xValues1 = new double[360];
        double[] yValues1 = new double[360];
        for (int i = 0; i < 360; i++) {
            double x = (i-180) * Math.PI / 180;
            xValues1[i] = x;
            yValues1[i] = 1/x;
        }
        Curve curve1 = new Curve(xValues1, yValues1);
        Curve curve = new Curve(xValues, yValues);
        Graph graph = new Graph();

        graph.addCurve(curve);
        graph.addCurve(curve1);


        var gd = new GraphDrawer(graph);
        gd.draw();
    }
}