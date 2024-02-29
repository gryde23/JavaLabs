package lab4.complex;

import java.util.Scanner;

public class Complex {
    private double x, y;
    private static final Scanner scanner = new Scanner(System.in);

    public static void task() {
        System.out.print("Задайте комплексное число: ");
        Complex z = new Complex();
        input(z);
        while (true) {
            System.out.print("""
                    Выберите действие:
                    0. Выход из программы
                    1. Вывести комплексное число
                    2. Вывести действительную часть
                    3. Вывести мнимую часть
                    4. Вывести сопряженное комплексное число
                    5. Сравнить два комплексных числа
                    6. Сложить два комплексных числа
                    7. Вычитание двух комплесных чисел
                    8. Произведение двух комплексных чисел
                    9. Частное двух комплесных чисел
                    10. Тригонометрическое представление комплексного числа.
                    Ввод:\s""");
            int input = scanner.nextInt();
            switch (input) {
                case 1 -> System.out.println("z = " + z.getComplex());
                case 2 -> System.out.println("x = " + z.getX());
                case 3 -> System.out.println("y = " + z.getY());
                case 4 -> System.out.println(SoprComplex(z).getComplex());
                case 5 -> {
                    System.out.println("Введите комплексное число для сравнения:");
                    Complex z2 = new Complex();
                    input(z2);
                    if (Comparison(z, z2)) {
                        System.out.println("z1 = z2");
                    } else System.out.println("z1 != z2");
                }
                case 6 -> {
                    System.out.println("Введите комплексное число для сложения:");
                    Complex z2 = new Complex();
                    input(z2);
                    System.out.println(SumComp(z, z2).getComplex());
                }
                case 7 -> {
                    System.out.println("Введите вычитаемое комплексное число:");
                    Complex z2 = new Complex();
                    input(z2);
                    System.out.println(SubComp(z, z2).getComplex());
                }
                case 8 -> {
                    System.out.println("Введите комплексное число для умножения");
                    Complex z2 = new Complex();
                    input(z2);
                    System.out.println(ProizComp(z, z2).getComplex());
                }
                case 9 -> {
                    System.out.println("Введите комплексное число - делитель:");
                    Complex z2 = new Complex();
                    input(z2);
                    System.out.println(DelComp(z, z2).getComplex());
                }
                case 10 -> System.out.println("z = " + TrgComp(z));
                default -> {
                    System.out.println("Действие программы завершено");
                    return;
                }
            }
        }
    }

    private static void input(Complex z) {
        System.out.print("\nx = ");
        z.setX(scanner.nextDouble());
        System.out.print("y = ");
        z.setY(scanner.nextDouble());
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getComplex() {
        return (this.x + " + i * " + this.y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private static boolean Comparison(Complex z1, Complex z2) {
        return (z1.x == z2.x && z1.y == z2.y);
    }

    private static Complex SoprComplex(Complex z) {
        Complex z2 = new Complex();
        z2.x = z.x;
        z2.y = -z.y;
        return z2;
    }

    private static Complex SumComp(Complex z1, Complex z2) {
        Complex z3 = new Complex();
        z3.x = z1.x + z2.x;
        z3.y = z1.y + z2.y;
        return z3;
    }

    private static Complex SubComp(Complex z1, Complex z2) {
        Complex z3 = new Complex();
        z3.x = z1.x - z2.x;
        z3.y = z1.y - z2.y;
        return z3;
    }

    private static Complex ProizComp(Complex z1, Complex z2) {
        Complex z3 = new Complex();
        z3.x = z1.x * z2.x - z1.y * z2.y;
        z3.y = z1.x * z2.y + z1.y * z2.x;
        return z3;
    }

    private static Complex DelComp(Complex z1, Complex z2) {
        Complex z3 = new Complex();
        z3.x = (z1.x * z2.x + z1.y * z2.y) / (Math.pow(z2.x, 2) + Math.pow(z2.y, 3));
        z3.y = (z1.y * z2.x - z1.x * z2.y) / (Math.pow(z2.x, 2) + Math.pow(z2.y, 3));
        return z3;
    }

    private static String TrgComp(Complex z) {
        double r = Math.pow((Math.pow(z.x, 2) + Math.pow(z.y, 2)), 0.5);
        double arg = Math.atan(z.y / z.x);
        return (r + " * (cos(" + arg + ") + i * sin (" + arg + "))");
    }
}
