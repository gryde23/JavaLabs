package lab4.complex;

import java.util.Scanner;

public record Complex(double x, double y) {
    private static final Scanner scanner = new Scanner(System.in);

    public static void task2() {
        System.out.println("Введите комплексное число: ");
        Complex z = Complex.input();
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
                case 2 -> System.out.println("x = " + z.x());
                case 3 -> System.out.println("y = " + z.y());
                case 4 -> System.out.println(z.SoprComplex().getComplex());
                case 5 -> {
                    System.out.println("Введите комплексное число для сравнения:");
                    Complex z2 = Complex.input();
                    if (Comparison(z, z2)) {
                        System.out.println("z1 = z2");
                    } else System.out.println("z1 != z2");
                }
                case 6 -> {
                    System.out.println("Введите комплексное число для сложения:");
                    Complex z2 = Complex.input();
                    System.out.println(z.SumComp(z2).getComplex());
                }
                case 7 -> {
                    System.out.println("Введите вычитаемое комплексное число:");
                    Complex z2 = Complex.input();
                    System.out.println(z.SubComp(z2).getComplex());
                }
                case 8 -> {
                    System.out.println("Введите комплексное число для умножения");
                    Complex z2 = Complex.input();
                    System.out.println(z.ProizComp(z2).getComplex());
                }
                case 9 -> {
                    System.out.println("Введите комплексное число - делитель:");
                    Complex z2 = Complex.input();
                    System.out.println(z.DelComp(z2).getComplex());
                }
                case 10 -> System.out.println("z = " + z.TrgComp());
                default -> {
                    System.out.println("Действие программы завершено");
                    return;
                }
            }
        }
    }

    private static Complex input() {
        System.out.print("x = ");
        double x = scanner.nextDouble();
        System.out.print("y = ");
        double y = scanner.nextDouble();
        return new Complex(x, y);
    }

    public String getComplex() {
        if (this.y < 0) {
            return (this.x + " - i*" + this.y);
        } else if (this.y == 0) {
            return Double.toString(this.x);
        } else if (this.x == 0) {
            return ("i*" + this.y);
        } else return (this.x + " + i*" + this.y);
    }


    private static boolean Comparison(Complex z1, Complex z2) {
        return (z1.x == z2.x && z1.y == z2.y);
    }

    private Complex SoprComplex() {
        return new Complex(this.x, -this.y);
    }

    private Complex SumComp(Complex z2) {
        double x = this.x + z2.x;
        double y = this.y + z2.y;
        return new Complex(x, y);
    }

    private Complex SubComp(Complex z2) {
        double x = this.x - z2.x;
        double y = this.y - z2.y;
        return new Complex(x, y);
    }

    private Complex ProizComp(Complex z2) {
        double x = this.x * z2.x - this.y * z2.y;
        double y = this.x * z2.y + this.y * z2.x;
        return new Complex(x, y);
    }

    private Complex DelComp(Complex z2) {
        double x = (this.x * z2.x + this.y * z2.y) / (Math.pow(z2.x, 2) + Math.pow(z2.y, 3));
        double y = (this.y * z2.x - this.x * z2.y) / (Math.pow(z2.x, 2) + Math.pow(z2.y, 3));
        return new Complex(x, y);
    }

    private String TrgComp() {
        double r = Math.pow((Math.pow(this.x, 2) + Math.pow(this.y, 2)), 0.5);
        double arg = Math.atan(this.y / this.x);
        return (r + " * (cos(" + arg + ") + i * sin (" + arg + "))");
    }

}