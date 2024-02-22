import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        var alphabet = Alphabet();
        System.out.print("Введите количество цепочек: ");
        int chains = scanner.nextInt();
        String[] res = Regular(chains, alphabet);
        for (String word: res) {
            System.out.println(word);
        }
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
        StringBuilder buff = new StringBuilder();
        do {
            del = wordCode % alf.length();
            if (del == 0) {
                del = alf.length();
                wordCode -= alf.length();
            }
            wordCode /= alf.length();
            buff.append(alf.charAt(del - 1));
        }
        while (wordCode != 0);

        StringBuilder word = new StringBuilder();
        for (int i = buff.length() - 1; i >= 0; i--) {
            word.append(buff.charAt(i));
        }
        return word.toString();
    }

    public static String[] Regular(int chains, String alphabet) {
        String word = "";
        String[] res = new String[chains];
        Pattern reg = Pattern.compile("02|20");
        int i = 1, j = 0;
        while(res[chains - 1] == null) {
            word = NumberToWord(alphabet, i);
            while (reg.matcher(word).find()) {
                i++;
                word = NumberToWord(alphabet, i);
            }
            res[j] = word;
            i++; j++;
        }
        return res;
    }
}