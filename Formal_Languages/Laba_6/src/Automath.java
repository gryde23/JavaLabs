import java.util.*;

public class Automath {
    private final static Scanner scanner = new Scanner(System.in);
    private final String alphabet;
    private ArrayList<String> States = new ArrayList<>();
    private ArrayList<String> end_states = new ArrayList<>();
    private Map<Integer, EqualClass> Equal_classes = new LinkedHashMap<>();
    private Map<String, String> automath = new LinkedHashMap<>();

    public Automath(Integer num_states) {
        this.alphabet = setAlphabet();
        for (int i = 0; i < num_states; i++) {
            this.States.add("q" + i);
        }
        setEnd_states();
    }

    public ArrayList<String> getEnd_states() {
        return end_states;
    }

    public void print() {
        System.out.print(" ".repeat(10));
        for (int i = 0; i < alphabet.length(); i++) {
            System.out.print("|" + " ".repeat(5) + alphabet.charAt(i) + " ".repeat(5));
        }
        System.out.println("|\n" + "-".repeat(12 * (alphabet.length() + 1)));
        for (String q : States) {
            if (q.equals("q0")) System.out.printf("-> %7s|", q);
            else if (end_states.contains(q)) System.out.printf("<- %7s|", q);
            else System.out.printf("%10s|", q);
            for (int i = 0; i < alphabet.length(); i++) {
                for (Map.Entry<String, String> entry : automath.entrySet()) {
                    String key = entry.getKey().substring(1, 3);
                    String simb = entry.getKey().substring(4, 5);
                    if (!q.equals(key) || !simb.equals(String.valueOf(alphabet.charAt(i)))) continue;
                    String value = entry.getValue();
                    System.out.printf("%11s|", value);
                    break;
                }
            }
            System.out.println();
        }
    }

    public void setEnd_states() {
        System.out.print("Конечные состояния: ");
        String buff = scanner.nextLine();
        ArrayList<String> end = new ArrayList<>(Arrays.asList(buff.split("\\s")));
        boolean flag = true;
        while (flag) {
            for (String state : end) {
                if (Objects.equals(state, "-")) break;
                if (!States.contains(state)) {
                    System.out.print("Некорректный ввод! Повторите попытку: ");
                    buff = scanner.nextLine();
                    end = new ArrayList<>(Arrays.asList(buff.split("\\s")));
                    flag = true;
                    break;
                }
                flag = false;
            }
        }
        this.end_states = end;
    }

    public static String setAlphabet() {
        System.out.print("Введите алфавит: ");
        String alf = scanner.nextLine();
        while (alf.length() < 2) {
            System.out.print("Некорректный алфавит! Повторите ввод: ");
            alf = scanner.nextLine();
        }

        var h = new HashSet<>();
        alf.chars().forEach(h::add);
        while (h.size() != alf.length()) {
            System.out.print("Некорректный алфавит! Повторите ввод: ");
            alf = scanner.nextLine();
        }
        return alf;
    }

    public void create() {
        Map<String, String> machine = new LinkedHashMap<>();
        String key = "";
        StringBuilder buff = new StringBuilder();
        String b;
        for (String state : States) {
            for (int i = 0; i < alphabet.length(); i++) {
                key = "(" + state + ";" + alphabet.charAt(i) + ")";
                System.out.print(key + "-> ");
                buff = new StringBuilder(scanner.nextLine());
                ArrayList<String> res = new ArrayList<>(Arrays.asList(buff.toString().split("\\s")));
                buff = new StringBuilder();
                boolean flag = true;
                while (flag) {
                    for (String stat : res) {
                        if (Objects.equals(stat, "-")) break;
                        if (!States.contains(stat)) {
                            System.out.print("Некорректный ввод! Повторите попытку: ");
                            b = scanner.nextLine();
                            res = new ArrayList<>(Arrays.asList(b.split("\\s")));
                            flag = true;
                            break;
                        }
                        flag = false;
                    }
                }
                for (int j = 0; j < res.size(); j++) {
                    if (j == res.size() - 1) buff.append(res.get(j));
                    else buff.append(res.get(j)).append(",");
                }
                machine.put(key, buff.toString());
            }

        }
        this.automath = machine;
    }

    public void deleteStates() {
        Set<String> visited = new LinkedHashSet<>();
        visited.add("q0");
        String state = "q0";
        s:while(true){
            for (Map.Entry<String, String> entry : automath.entrySet()) {
                if (entry.getKey().contains(state) && !visited.contains(entry.getValue())) {
                    visited.add(entry.getValue());
                    state = entry.getValue();
                    continue s;
                }
            }
            break;
        }
        List<String> list = new ArrayList<>(visited);
        Collections.sort(list);
        visited = new LinkedHashSet<>(list);
        States = new ArrayList<>(visited);
        del:
        while (States.size() * alphabet.length() != automath.size()) {
            for (Map.Entry<String, String> entry : automath.entrySet()) {
                String q = entry.getKey().substring(1, 3);
                if (!States.contains(q)) {
                    automath.remove(entry.getKey());
                    break;
                }
            }
            for (String q: end_states) {
                if (!States.contains(q)) {
                    end_states.remove(q);
                    break;
                }
            }
        }
    }
}
