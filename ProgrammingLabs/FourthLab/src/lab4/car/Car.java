package lab4.car;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Car {

    private final Scanner scanner = new Scanner(System.in);
    private String RegNumber;
    private String Mark, Color;
    private int Power, Wheels;
    public Car(final String mark, String color, int power,final int wheels,final Types_of_Cars type) {
        this.Wheels = wheels;
        this.Power = power;
        this.Mark = mark;
        this.Color = color;
    }

    public int getPower() {
        return Power;
    }

    public void setRegNumber() {
        System.out.print("Введите номер автомобиля: ");
        String RegNum = scanner.nextLine();
        Pattern Numbers = Pattern.compile("[АВЕКМНОРСТУХ]\\s?\\d{3}\\s?[АВЕКМНОРСТУХ]{2}\\s?\\d{2,3}\\s?RUS");
        Matcher m = Numbers.matcher(RegNum);
        while (!m.find()) {
            System.out.print("Некорректно задан номер авто! Повторите попытку: ");
            RegNum = scanner.nextLine();
            m = Numbers.matcher(RegNum);
        }
        this.RegNumber = RegNum;
    }
}
