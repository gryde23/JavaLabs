
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println();
        while (true) {
            System.out.print("Введите номер задания: ");
            int input = scanner.nextInt();
            switch (input) {
                case 1 -> {
                    System.out.println("////////////////Task 1////////////////\n");
                    task1();
                }
                case 2 -> {
                    System.out.println("\n////////////////Task 2////////////////\n");
                    task2();
                }
                case 3 -> {
                    System.out.println("\n////////////////Task 3////////////////\n");
                    task3();
                }
                case 4 -> {
                    System.out.println("\n////////////////Task 4////////////////\n");
                    task4();
                }
                case 5 -> {
                    System.out.println("\n////////////////Task 5////////////////\n");
                    task5(args);
                }
                case 6 -> {
                    System.out.println("\n////////////////Task 6////////////////\n");
                    task6(0x0400, 16, 16);
                    task6(0x02a0, 2, 16);
                }
                case 7 -> {
                    System.out.println("\n////////////////Task 7////////////////\n");
                    task7();
                }
                case 9 -> {
                    System.out.println("\n////////////////Task 9////////////////\n");
                    task9();
                }
                case 10 -> {
                    System.out.println("\n////////////////Task 10////////////////\n");
                    task10();
                }
                default -> {
                    return;
                }
            }
        }

    }

    public static void task1() {
        System.out.println("type \t\tmin \t\tmax");
        System.out.println("byte\t\t" + Byte.MIN_VALUE + "\t\t" + Byte.MAX_VALUE);
        System.out.println("short\t\t" + Short.MIN_VALUE + "\t\t" + Short.MAX_VALUE);
        System.out.println("int\t\t" + Integer.MIN_VALUE + "\t\t" + Integer.MAX_VALUE);
        System.out.println("long\t\t" + Long.MIN_VALUE + "\t\t" + Long.MAX_VALUE);
        System.out.println("float\t\t" + Float.MIN_VALUE + "\t\t" + Float.MAX_VALUE);
        System.out.println("double\t\t" + Double.MIN_VALUE + "\t\t" + Double.MAX_VALUE);
    }

    public static void task2() {
        float avg = 1;
        float count = 0;
        int[] array = {1, 2, -3, -3, 4, 5, 6, 7, 8, 9, 10};
        for (int j : array) {
            if (j < 0) {
                avg *= Math.abs(j);
                count++;
            }
        }
        System.out.println("Массив: " + Arrays.toString(array));
        System.out.println("Среднее геометрическое отрицательных элеметов: " + Math.pow(avg, (1 / count)));
    }

    public static void task3() {
        int R = 100;
        int r = 50;
        double a = Math.random() * 121;
        double b = Math.random() * 121;
        var coord = Math.pow((Math.pow(a, 2) + Math.pow(b, 2)), 0.5);
        if (coord > R) {
            System.out.println("a = " + a + "\tb = " + b + "\nОбъект не обнаружен");
        } else {
            if (coord <= r) {
                System.out.println("a = " + a + "\tb = " + b + "\nТревога!");
            } else System.out.println("a = " + a + "\tb = " + b + "\nОбъект обнаружен");
        }
    }

    public static void task4() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Значение R: ");
        int R = scanner.nextInt();

        System.out.print("\n Значение r: ");
        int r = scanner.nextInt();

        System.out.print("\n Координата x обьекта: ");
        double a = scanner.nextDouble();

        System.out.print("\n Координата y обьекта: ");
        double b = scanner.nextDouble();
        var coord = Math.pow((Math.pow(a, 2) + Math.pow(b, 2)), 0.5);

        if (coord > R) {
            System.out.println("a = " + a + "\tb = " + b + "\nОбъект не обнаружен");
        } else {
            if (coord <= r) {
                System.out.println("a = " + a + "\tb = " + b + "\nТревога!");
            } else System.out.println("a = " + a + "\tb = " + b + "\nОбъект обнаружен");
        }
    }

    public static void task5(String[] args) {
        int num = Integer.parseInt(args[0]);

        String binaryNum = Integer.toBinaryString(num);
        String octNum = Integer.toOctalString(num);
        String hexNum = Integer.toHexString(num);

        System.out.println("Число " + num + " в 2-чной системе счисления: " + binaryNum);
        System.out.println("Число " + num + " в 8-чной системе счисления: " + octNum);
        System.out.println("Число " + num + " в 16-ной системе счисления: " + hexNum);

    }

    public static void task6(int start, int rows, int cols) {
        System.out.println(
                "     " + IntStream.range(0, cols)
                        .mapToObj(x -> Integer.toString(x, 16))
                        .collect(Collectors.joining("  "))
        );
        for (int i = 0; i < rows; i++) {
            var row = new StringBuilder(String.format("%4X ", start + i * cols));
            for (int j = 0; j < cols; j++) {
                row.append((char) (start + i * cols + j)).append("  ");
            }
            System.out.println(row);
        }
    }

    public static void task7() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String input = scanner.nextLine();

        int letterCount = 0, upperCaseCount = 0, lowerCaseCount = 0, digitCount = 0, arabicDigitCount = 0, nonArabicDigitCount = 0, otherSymbolsCount = 0;

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (Character.isLetter(ch)) {
                letterCount++;
                if (Character.isUpperCase(ch)) {
                    upperCaseCount++;
                } else if (Character.isLowerCase(ch)) {
                    lowerCaseCount++;
                }
            } else if (Character.isDigit(ch)) {
                digitCount++;
                if ((ch >= '0' && ch <= '9')) {
                    arabicDigitCount++;
                } else {
                    nonArabicDigitCount++;
                }
            } else {
                otherSymbolsCount++;
            }
        }

        System.out.println("Количество букв: " + letterCount);
        System.out.println("Количество строчных букв: " + lowerCaseCount);
        System.out.println("Количество прописных букв: " + upperCaseCount);
        System.out.println("Количество цифр: " + digitCount);
        System.out.println("Количество арабских цифр: " + arabicDigitCount);
        System.out.println("Количество не арабских цифр: " + nonArabicDigitCount);
        System.out.println("Количество других символов: " + otherSymbolsCount);
        System.out.println("Общее количество символов: " + input.length());
    }

    public static void task9() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String stroka = scanner.nextLine();

        System.out.print("Введите подстроку: ");
        String podstroka = scanner.nextLine();
        int index = -1, count = 0;
        while (stroka.indexOf(podstroka, index + 1) != -1) {
            index = stroka.indexOf(podstroka, index + 1);
            count++;
        }
        System.out.printf("Подстрока " + podstroka + " встречается в строке " + stroka + " " + count + " раз");
    }

    public static void task10() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String input = scanner.nextLine();

        for (int i = 0; i < input.length() - 1; i++) {
            var buff = input.charAt(0);
            input = input.substring(1);
            input += buff;
            System.out.println(input);
        }
    }
}

