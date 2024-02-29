import lab4.car.Car;
import lab4.car.Types_of_Cars;
import lab4.complex.Complex;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.print("Введите номер задания: ");
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 1 -> {
                    Car avto = new Car();
                    avto.Changes();
                }
                case 2 -> {
                    Complex.task();
                }
                default -> {
                    return;
                }
            }

        }
    }
}
