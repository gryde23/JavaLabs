//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    Automath automath = new Automath(7);
    automath.create();
    automath.print();
    automath.deleteStates();
    automath.print();
    }
}