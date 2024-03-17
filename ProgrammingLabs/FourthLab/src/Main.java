import lab4.car.*;
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
                    Complex z1 = new Complex(3,4);
                    Complex z2 = new Complex(1,2);
                    System.out.println(z1);
                    System.out.println(z2);
                    System.out.println(z1.add(z2));
                    System.out.println(z1.divide(z2));
                    System.out.println(z1.equals(z2));
                    System.out.println(z1.multiply(z2));
                    System.out.println(z1.toTrigString());
                }
                case 3 -> {

                }
                case 7 -> {
                    Engine passengerEngine = new Engine(6736, 150, 20, 5, "oil");
                    Engine busEngine = new Engine(8591, 400, 40, 10, "diesel");
                    Engine truckEngine = new Engine(5295, 1200, 120, 30, "diesel");
                    Passenger hyundai = new Passenger("Hyundai", "black", passengerEngine, 4, "А123ВС161RUS");
                    Bus icarus = new Bus("Icarus", "gray", busEngine, 4, "В525СР161RUS");
                    Truck scania = new Truck("Scania", "red", truckEngine, 6, "Е578РО161RUS");
                    Autobase autobase = new Autobase(10);
                    autobase.addCarOnBase(hyundai);
                    autobase.addCarOnBase(icarus);
                    autobase.addCarOnBase(scania);
                    autobase.getCarsOnBase();
                    autobase.carToRepair(icarus);
                    autobase.carToTrip(scania);
                    autobase.getCarsOnBase();
                    autobase.getCarsOnRepair();
                    autobase.getCarsOnTrip();
                    autobase.carFromRepair(icarus);
                    autobase.getCarsOnBase();
                    autobase.getCarsOnRepair();
                }
                default -> {
                    return;
                }
            }

        }
    }
}
