import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 1;
        while (choice == 1 || choice == 2) {
            System.out.print("\nВыберите действие:\nВывод лексико-графического номера слова - введите 1\nВывод слова по лексико-графическому номеру - введите 2\nВвод: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("-------------Вывод лексико-графического номера слова-------------");
                    part1();
                    break;
                case 2:
                    System.out.println("\n-------------Вывод слова по лексико-графическому номеру-------------");
                    part2();
                    break;
                default:
                    System.out.println("Такого действия нет. Программа завершена.");
            }
        }
    }

    public static void part1() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите алфавит: ");
        String alf = scanner.nextLine();
        while (alf.length() < 2) {
            System.out.print("Алфавит слишком короткий, повторите ввод: ");
            alf = scanner.nextLine();
        }

        var h = new HashSet<>();
        alf.chars().forEach(h::add);
        while (h.size() != alf.length()){
            System.out.print("Некорректный алфавит! Повторите ввод: ");
            alf = scanner.nextLine();
        }
        System.out.print("Введите слово, чтобы узнать его лексико-графический номер: ");
        String word = scanner.nextLine();

        var flag = 0;
        do {
            for (int i = 0; i < word.length(); i++) {
                if (!alf.contains(String.valueOf(word.charAt(i)))) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                System.out.print("Некорректное слово! Повторите ввод: ");
                word = scanner.nextLine();
                flag = 0;
            }
            else flag = 2;

        } while (flag == 0);

        int alfLen = alf.length();
        int num = 0;
        int k = 0;
        for (int i = word.length() - 1; i >= 0; i--) {
            int simbCode = (alf.indexOf(word.charAt(k)) + 1);
            if (i > 0)System.out.print(alf.length() + "^" + i + " * " + simbCode + " + ");
            else System.out.print(alf.length() + "^" + i + " * " + simbCode);
            num += ((int) Math.pow(alfLen, i)) * simbCode;
            k++;
        }
        System.out.println("\nЛексико-графический номер слова " + word + ": " + num);
    }

    public static void part2() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите алфавит: ");
        String alf = scanner.nextLine();
        while (alf.length() < 2) {
            System.out.print("Алфавит слишком короткий, повторите ввод: ");
            alf = scanner.nextLine();
        }

        System.out.print("Введите лексико-графический номер: ");
        int wordCode = scanner.nextInt();
        int code = wordCode;

        int del;
        String buff = "";
        do {
            del = wordCode % alf.length();
            if (del == 0) {
                del = alf.length();
                wordCode -= alf.length();
                System.out.println(wordCode / alf.length() + " * " + alf.length() + " + " + del);
            } else System.out.println(wordCode / alf.length() + " * " + alf.length() + " + " + del);
            wordCode /= alf.length();
            buff += alf.charAt(del - 1);
        }
        while (wordCode >= alf.length());

        String word = "";
        for (int i = buff.length() - 1; i >= 0; i--) {
            word += buff.charAt(i);
        }

        System.out.println("Слово по номеру " + code + ": " + word);
    }
}