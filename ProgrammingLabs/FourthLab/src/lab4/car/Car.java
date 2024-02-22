package lab4.car;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Car {

    private final Scanner scanner = new Scanner(System.in);
    private String RegNumber, Color;
    private final String Mark;
    private int Power;
    private final int Wheels;
    private final Types_of_Cars Type;

    public Car() {
        System.out.println("Задайте характеристики авто.");
        System.out.print("Марка: ");
        this.Mark = scanner.nextLine();
        System.out.print("Цвет: ");
        this.Color = scanner.nextLine();
        System.out.print("Мощность двигателя: ");
        this.Power = scanner.nextInt();
        System.out.print("Количество колес: ");
        this.Wheels = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Тип(Легковой - 0, Грузовой - 1, Автобус - 2): ");
        int type = scanner.nextInt();
        while (type > 2 || type < 0) {
            System.out.print("Такого типа нет. Повторите ввод: ");
            type = scanner.nextInt();
        }
        this.Type = Types_of_Cars.values()[type];
        scanner.nextLine();
        System.out.print("Регистрационный номер(если нет введите '-'): ");
        String num = scanner.nextLine();
        if (!Objects.equals(num, "-")) checkRegNumber(num);
    }

    public void getAllCharacteristics() {
        System.out.printf("""
                Марка: %s
                Цвет: %s
                Мощность двигателя: %d
                Количество колес: %d
                Тип: %s
                Номер: %s
                """, getMark(), getColor(), getPower(), getWheels(), getType(), getRegNumber());

    }

    public int getPower() {
        return Power;
    }

    public int getWheels() {
        return Wheels;
    }

    public String getRegNumber() {
        return RegNumber;
    }

    public String getColor() {
        return Color;
    }

    public String getMark() {
        return Mark;
    }

    public Types_of_Cars getType() {
        return Type;
    }

    public void Changes() {
        System.out.print("Изменились какие-либо характеристики авто? Ответ: ");
        String answer = scanner.nextLine();
        Pattern yes = Pattern.compile("Да|да|yes|Yes");
        Matcher m = yes.matcher(answer);
        if (!m.find()) {
            System.out.println("Текущие характеристики авто:");
            getAllCharacteristics();
            return;
        }
        while (true) {
            System.out.print("Что конкретно изменилось(Цвет - 1, Мощность двигателя - 2, Регистрационный номер - 3, Ничего - 0): ");
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 1 -> {
                    System.out.print("Новый цвет авто: ");
                    this.Color = scanner.nextLine();
                }
                case 2 -> {
                    System.out.print("Мощность двигателя: ");
                    this.Power = scanner.nextInt();
                }
                case 3 -> {
                    System.out.print("Регистрационный номер: ");
                    String num = scanner.nextLine();
                    checkRegNumber(num);
                }
                default -> {
                    System.out.println("Изменения сохранены.Новые характеристики авто:");
                    getAllCharacteristics();
                    return;
                }
            }
        }
    }

    private void checkRegNumber(String RegNum) {
        Pattern Numbers = Pattern.compile("[АAВBЕEКKМMНHОOРPСCТTУYХX]\\s?\\d{3}\\s?[АAВBЕEКKМMНHОOРPСCТTУYХX]{2}\\s?\\d{2,3}\\s?RUS");
        Matcher m = Numbers.matcher(RegNum);
        while (!m.find()) {
            System.out.print("Некорректно задан номер авто! Повторите попытку: ");
            RegNum = scanner.nextLine();
            m = Numbers.matcher(RegNum);
        }
        this.RegNumber = RegNum;
    }
}
