import java.util.HashSet;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        var alphabet = Alphabet();
        System.out.print("Введите количество цепочек: ");
        int chains = scanner.nextInt();
    }

    public static String Alphabet(){
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

        return alf;
    }

    public static String NumberToWord(String alf, int wordCode) {
        int del;
        String buff = "";
        do {
            del = wordCode % alf.length();
            if (del == 0) {
                del = alf.length();
                wordCode -= alf.length();
            }
            wordCode /= alf.length();
            buff += alf.charAt(del - 1);
        }
        while (wordCode >= alf.length());

        String word = "";
        for (int i = buff.length() - 1; i >= 0; i--) {
            word += buff.charAt(i);
        }
        return word;
    }

    public static void Regular() {

    }
}