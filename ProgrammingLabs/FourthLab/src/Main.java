import lab4.car.Car;
import lab4.car.Engine;
import lab4.car.Passenger;
import lab4.complex.Complex;
import lab4.complex.ComplexFunc;

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
                    Passenger avto = new Passenger("Lada", "white", new Engine(57652, 200, 20, 10, "gas"),4);
                    System.out.println(avto);
                    avto.setRegNumber("А412ВС161RUS");
                    avto.setColor("black");
                    System.out.println(avto);
                }
                case 2 -> {
                    Complex.task2();
                }
                case 3 -> {
                    System.out.println(ComplexFunc.sin(new Complex(2,3)));
                }
                default -> {
                    return;
                }
            }

        }
    }
}
