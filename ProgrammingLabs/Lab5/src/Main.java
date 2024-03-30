import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите номер задания: ");
            int input = scanner.nextInt();
            switch (input) {
                case 1 -> {
                    Pair<Integer, Integer> pair = new Pair<>(3,4);
                    System.out.println(pair);
                    pair.setFirst(4);
                    System.out.println(pair);
                    System.out.println(Pair.make_pair("abc", 1));
                }
                case 2 -> {
                    Bag bag = new Bag(10);
                    bag.addElement(10);
                    bag.addElement("apple");
                    bag.addElement('p');
                    bag.addElement(Pair.make_pair(3,12));
                    bag.addElement(23);
                    bag.addElement("faaf");
                    bag.addElement(Pair.make_pair("red", "blue"));
                    bag.addElement(0);
                    bag.addElement(true);
                    bag.addElement(false);
                    bag.addElement(true);
                    System.out.println(bag);
                    System.out.println(bag.getBagSize());
                    System.out.println(bag.removeElem());
                    System.out.println(bag);
                    System.out.println(bag.getBagSize());
                    bag.addElement("NEW");
                    System.out.println(bag);
                    System.out.println(bag.getElem());
                }
                case 3 -> {
                    PairBag pairBag = new PairBag(10);
                    for (int i = 0; i < 10; i++) {
                        pairBag.addPair(new Pair<>(i,i));
                    }
                    System.out.println(pairBag);
                }
                case 4 -> {
                    GPairBag<String> gPairBag = new GPairBag<>(10);
                    gPairBag.addPair(new Pair<>("a", "b"));
                    gPairBag.addPair(new Pair<>("c", "d"));
                    gPairBag.addPair(new Pair<>("e", "f"));
                    System.out.println(gPairBag);
                    System.out.println(gPairBag.getElem());
                }
                case 5 -> {
                    GenericPairBag<Integer> genericPairBag = new GenericPairBag<>();
                    genericPairBag.addPair(new Pair<>(1,1));
                    genericPairBag.addPair(new Pair<>(2,2));
                    genericPairBag.addPair(new Pair<>(3,3));
                    genericPairBag.addPair(new Pair<>(4,4));
                    System.out.println(genericPairBag);
                    System.out.println(genericPairBag.getPair());
                }
                default -> {
                    System.out.println("Программа завершена");
                    return;
                }
            }
        }
    }
}