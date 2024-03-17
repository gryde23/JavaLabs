

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.print("\nВведите номер задания: ");
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 1 -> task1();
                case 2 -> task2();
                case 3 -> task3();
                case 4 -> tsk4();
                case 5 -> tsk5();
                case 6 -> task6();
                case 7 -> task7();
                case 8 -> task8();
                case 9 -> task9();
                case 10 -> task10();
                default -> {
                    System.out.println("Такого задания нет. Программа завершена.");
                    scanner.close();
                    return;
                }
            }
        }
    }

    public static void task1() {
        System.out.printf("%7s %15s %20s\n", "x", "sin(x)", "e^x / x * lg (x)");
        for (var x = Math.PI / 15; x < Math.PI; x += Math.PI / 15) {
            System.out.printf("%10.5f %15.7e %15.7e\n", x, Math.sin(x), Math.exp(x) / x * Math.log10(x));
        }
    }

    public static void task2() {
        Random random = new Random();
        int rows = random.nextInt(10) + 1, columns = random.nextInt(10) + 1;
        int[][] array = new int[rows][];
        for (int i = 0; i < rows; i++) {
            array[i] = new int[columns];
            for (int j = 0; j < columns; j++) {
                array[i][j] = random.nextInt(100) - 50;
                System.out.printf("%5d ", array[i][j]);
            }
            columns = random.nextInt(10) + 1;
            System.out.print("\n");
        }

        int maxNeg = Integer.MIN_VALUE;
        for (int[] row : array) {
            for (int num : row) {
                if (num < 0 && num > maxNeg) maxNeg = num;
            }
        }
        System.out.printf("Максимальное отрицательное число в массиве: %5d\n", maxNeg);
    }

    public static void bubblesort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void task3() {
        Random random = new Random();
        int[][] matrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = random.nextInt(20);
                System.out.printf("%5d", matrix[i][j]);
            }
            System.out.print("\n");
        }
        for (int[] row : matrix) {
            bubblesort(row);
            System.out.println(Arrays.toString(row));
        }
    }

    public static void tsk4() {
        System.out.print("Введите через пробел значения x1, y1, r1 и x2, y2, r2:");

        String input = scanner.nextLine();
        String[] data = input.split("\\s+");
        while (data.length != 6) {
            System.out.print("Некорректный ввод! Повторите попытку: ");
            input = scanner.nextLine();
            data = input.split("\\s+");
        }

        task4 t = new task4();
        t.setX1(Integer.parseInt(data[0]));
        t.setY1(Integer.parseInt(data[1]));
        t.setR1(Integer.parseInt(data[2]));
        t.setX2(Integer.parseInt(data[3]));
        t.setY2(Integer.parseInt(data[4]));
        t.setR2(Integer.parseInt(data[5]));
        int res = t.task();
        System.out.print(res + "\n");
    }

    public static void tsk5() {
        System.out.print("Введите через пробел значения x1, y1, r1 и x2, y2, r2:");

        String input = scanner.nextLine();
        String[] data = input.split("\\s+");
        while (data.length != 6) {
            System.out.print("Некорректный ввод! Повторите попытку: ");
            input = scanner.nextLine();
            data = input.split("\\s+");
        }

        task5 t = new task5();
        t.setX1(Integer.parseInt(data[0]));
        t.setY1(Integer.parseInt(data[1]));
        t.setR1(Integer.parseInt(data[2]));
        t.setX2(Integer.parseInt(data[3]));
        t.setY2(Integer.parseInt(data[4]));
        t.setR2(Integer.parseInt(data[5]));
        task5.Task5 res = t.task();
        System.out.print(res + "\n");
    }

    public static double Integral(double[] x, double[] y) {
        double res = 0, h = x[1] - x[0];
        for (int i = 0; i < 101; i++) {
            res += h * y[i];
        }
        return res;
    }

    public static void task6() {
        System.out.print("Задайте интервал через пробел: ");
        String input = scanner.nextLine();
        String[] interval = input.split("\\s+");
        int begin = Integer.parseInt(interval[0]), end = Integer.parseInt(interval[1]);
        while (interval.length != 2 || begin > end) {
            System.out.print("Некорректный ввод! Повторите попытку: ");
            input = scanner.nextLine();
            interval = input.split("\\s+");
            begin = Integer.parseInt(interval[0]);
            end = Integer.parseInt(interval[1]);
        }
        double len = end - begin;
        double[] x = new double[101], y = new double[101];
        x[0] = begin;
        y[0] = Math.exp(begin) + Math.pow(begin, 3);
        System.out.printf("%7s %20s\n%10.3f %20.3f\n", "x", "y", x[0], y[0]);
        for (int i = 1; i < 101; i++) {
            x[i] = begin + i * (len / 101);
            y[i] = Math.exp(x[i]) - Math.pow(x[i], 3);
            System.out.printf("%10.3f %20.3f\n", x[i], y[i]);
        }

        double res = Integral(x, y);
        System.out.printf("Значение интеграла: %5.3f\n", res);
    }

    public static void task7() {
        System.out.print("Введите число: ");
        int num = scanner.nextInt(), buf = num;
        System.out.print("Введите систему счисления: ");
        int system = scanner.nextInt();
        StringBuilder result = new StringBuilder();
        while (num / system > 0) {
            result.append(num % system);
            num /= system;
        }
        result.append(num);
        result.reverse();
        System.out.printf("Число %d в %d системе счисления(алгоритм): %s\n", buf, system, result);
        System.out.println("С помощью метода toString(): " + Integer.toString(buf, system));
    }

    public static void task8() {
        System.out.print("Введите x: ");
        double x = scanner.nextDouble();
        System.out.print("Введите степень полинома: ");
        int grade = scanner.nextInt();
        int[] a = new int[grade];
        Random random = new Random();
        for (int i = 0; i < grade; i++) {
            a[i] = random.nextInt(20);
        }
        System.out.println(Arrays.toString(a));
        double P = a[grade - 1] * x + a[grade - 2];

        for (int i = grade - 3; i >= 0; i--) {
            P = P * x + a[i];
        }
        System.out.println("Значение полинома: " + P);
    }

    public static void task9() {
        Pattern p = Pattern.compile("((\\+7|8)[-\\s(]?\\d{3}[-\\s)]?\\s?\\d{3}([-\\s]?\\d{2}){2})|([23][\\s-]?([\\s-]?\\d{2}){3})");
        String numbers = "89043781661 +79043781661 +7 904 378 1661  +7 904 378 16 61" +
                "+7-904-378-16-61 +7(904)3781661 +7(904) 378-16 61 " +
                " 8 904 378-16-61 +7989(199)35-92 220-30-40 3-04-23-52 3 45 52 42 3 43-53-65";
        Matcher m = p.matcher(numbers);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            System.out.println(numbers.substring(start, end));
        }
    }

    public static void task10(){
        Pattern p = Pattern.compile("((\\+7|8)[-\\s(]?\\d{3}[-\\s)]?\\s?\\d{3}([-\\s]?\\d{2}){2})|([23][\\s-]?([\\s-]?\\d{2}){3})");
        String text = "Мои номера 220-30-40 и 8904-378-16-61 не считая служебных dfb 89032345656 2345656 234567";
        Matcher m = p.matcher(text);
        while(m.find()){
            int begin = m.start();
            int end = m.end();
            System.out.println(text.substring(begin, end));
        }
    }
}

