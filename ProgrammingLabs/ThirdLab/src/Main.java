

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nВведите номер задания: ");
            int input = 0;
            if (scanner.hasNextInt()) input = scanner.nextInt();
            switch (input) {
                case 1 -> task1();
                case 2 -> task2();
                case 3 -> task3();
                default -> {
                    System.out.println("Такого задания нет. Программа завершена.");
                    return;
                }
            }
        }
    }

    public static void task1() {
        System.out.println("------------Task 1------------");
        System.out.printf("%7s %15s %20s\n", "x", "sin(x)", "e^x / x * lg (x)");
        for (var x = Math.PI / 15; x < Math.PI; x += Math.PI/15) {
            System.out.printf("%10.5f %15.7e %15.7e\n", x, Math.sin(x), Math.exp(x) / x * Math.log10(x));
        }
    }

    public static void task2() {
        System.out.println("------------Task 2------------");
        Random random = new Random();
        int rows = random.nextInt(10) + 1, columns = random.nextInt(10) + 1;
        int[][] array = new int[rows][];
        for (int i = 0; i < rows; i++) {
            array[i] = new int[columns];
            for (int j = 0; j < columns; j++) {
                array[i][j] = random.nextInt(100)  - 50;
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
}

