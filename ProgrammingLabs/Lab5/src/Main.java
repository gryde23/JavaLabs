import java.util.ArrayList;
import java.util.List;
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
                case 6 -> {
                    System.out.print("Кол-во участников: ");
                    int N = scanner.nextInt();
                    if (N % 2 != 0) {
                        System.out.print("Некорректный ввод. Задано значение по умолчанию - N = 8");
                        N = 8;
                    }
                    Bag first = new Bag(N);
                    for (int i = 0; i < N; i++){
                        first.addElement("Команда" + i);
                    }
                    while (N != 1) {
                        first.shake();
                        System.out.println("Участники: " + first);
                        GenericPairBag<String> second = new GenericPairBag<>();
                        N /= 2;
                        for (int i = 0; i < N; i++){
                            String t1 = first.removeElem().toString();
                            String t2 = first.removeElem().toString();
                            second.addPair(new Pair<>(t1,t2));
                        }
                        first = new Bag(N);
                        System.out.println("Tournament");
                        for (int i = 0; i < N;i++){
                            Pair<String,String> pair = second.removePair();
                            System.out.println(pair);
                            System.out.print("Кто победил(1/2): ");
                            int w = scanner.nextInt();
                            if (w == 1) first.addElement(pair.getFirst());
                            else first.addElement(pair.getSecond());
                        }
                    }
                    System.out.println("Победитель:" + first);
                }
                case 7 -> {
                    DList<Integer, Integer> dList = new DList<>();
                    dList.add(2, List.of(4));
                    dList.add(4, List.of(16));
                    dList.add(8, List.of(64));
                    System.out.println(dList.getByInd(0));
                    System.out.println(dList.getByKey(4));
                    dList.removeByKey(2);
                    System.out.println(dList.getByInd(0));
                }
                case 8 -> {
                    int[] nominals = {1,2,5,10};
                    int Sum = 9;
                    DList<Integer, Integer> res = new DList<>();
                    for (int i = 1; i <= Sum; i++){
                        int t = i;
                        List<Integer> s = new ArrayList<>();
                        for (int j = nominals.length - 1; j >= 0; j--){
                            if (t < nominals[j]) continue;
                            int amount = t / nominals[j];
                            for (int a = 0; a < amount; a++){
                                s.add(nominals[j]);
                            }
                            t -= nominals[j] * amount;
                        }
                        res.add(i, s);
                    }
                    System.out.println(res);
                }
                case 10 -> {
                    HashFunction<String> hashFunction = new PersonHashFunction(10);
                    HashTable<Person,String> hashTable = new PersonHashTable(10, hashFunction);
                    Person per1 = new Person("Ivan", "Ivanov", 32);
                    Person per2 = new Person("Oleg", "Vorobiev", 23);
                    Person per3 = new Person("Nikita", "Smirnov", 14);
                    hashTable.add(per1);
                    hashTable.add(per2);
                    hashTable.add(per3);
                    List<Person> persons = hashTable.get("Nikita14Smirnov");
                    for (Person person : persons) {
                        System.out.println(person.name() + " " + person.surname() +" " + person.age());
                    }
                }
                default -> {
                    System.out.println("Программа завершена");
                    return;
                }
            }
        }
    }
}