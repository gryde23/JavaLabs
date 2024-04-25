import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Terminals: ");
        String input = scanner.nextLine();
        String[] terms = input.split(" ");
        ArrayList<String> terminals = new ArrayList<>(List.of(terms));
        System.out.print("Nonterminals: ");
        input = scanner.nextLine();
        String[] nonterms = input.split(" ");
        ArrayList<String> nonterminals = new ArrayList<>(List.of(nonterms));
        ArrayList<Pair<String,String>> rules = new ArrayList<>();
        System.out.print("Rules: \nS -> ");
        input = scanner.nextLine();
        rules.add(new Pair<>("S", input));
        while(true){
            input = scanner.nextLine();
            if (input.equals("exit")) break;
            String[] rule = input.split("->");
            rules.add(new Pair<>(rule[0], rule[1]));
        }
        Gram gram = new Gram(rules, terminals, nonterminals);
        if (gram.leftLine()) {
            System.out.println("Грамматика является леволинейной и относится к 3 классу");
        } else if (gram.rightLine()) {System.out.println("Грамматика является праволинейной и относится к 3 классу");
        } else if (gram.contextFree()) { System.out.println("Грамматика является контекстно-свободной и относится ко 2 классу");
        } else if (gram.contextDependent()) { System.out.println("Грамматика является контекстно-зависимой и относится к 1 классу");
        } else if (gram.unshortenable()) { System.out.println("Грамматика является неукорачивающей и относится к 1 классу");
        } else System.out.println("Грамматика относится к 0 классу");
    }
}