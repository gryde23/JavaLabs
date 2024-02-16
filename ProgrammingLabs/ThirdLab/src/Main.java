//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        task1();
    }

    public static void task1() {
        System.out.println("------------Task 1------------");
        System.out.printf("%7s %15s %20s\n", "x", "sin(x)", "e^x / x * lg (x)");
        for (var x = Math.PI / 15; x < Math.PI; x += Math.PI/15) {
            System.out.printf("%10.5f %15.7e %15.7e\n", x, Math.sin(x), Math.exp(x) / x * Math.log10(x));
        }
    }
}

