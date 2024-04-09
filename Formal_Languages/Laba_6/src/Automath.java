import java.util.*;

public class Automath {
    private final static Scanner scanner = new Scanner(System.in);
    private final String alphabet;
    private ArrayList<String> States = new ArrayList<>();
    private ArrayList<String> end_states = new ArrayList<>();
    private final Set<EqualClass> Equal_classes = new LinkedHashSet<>();
    private Map<String, String> automath = new LinkedHashMap<>();

    public Automath(Integer num_states) {
        this.alphabet = setAlphabet();
        for (int i = 0; i < num_states; i++) {
            this.States.add("q" + i);
        }
        setEnd_states();
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

    public String getAlphabet() {
        return alphabet;
    }

    public Set<EqualClass> getEqual_classes() {
        return Equal_classes;
    }

    public Map<String, String> getAutomath() {
        return automath;
    }

    public ArrayList<String> getEnd_states() {
        return end_states;
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
        s:
        while (true) {
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
        while (States.size() * alphabet.length() != automath.size()) {
            for (Map.Entry<String, String> entry : automath.entrySet()) {
                String q = entry.getKey().substring(1, 3);
                if (!States.contains(q)) {
                    automath.remove(entry.getKey());
                    break;
                }
            }
            for (String q : end_states) {
                if (!States.contains(q)) {
                    end_states.remove(q);
                    break;
                }
            }
        }
    }

    public void ZeroClass() {
        Equal_classes.add(new EqualClass(end_states, "C1"));
        ArrayList<String> c2 = new ArrayList<>();
        for (String q : States) {
            if (!end_states.contains(q)) c2.add(q);
        }
        Equal_classes.add(new EqualClass(c2, "C2"));
    }

    public boolean breakClasses(int num) {
        System.out.print("\n" + num + " = ");
        for (EqualClass cl : Equal_classes) {
            System.out.print(cl.getStates() + ", ");
        }
        Set<EqualClass> next_EqualClasses = new LinkedHashSet<>();
        for (EqualClass equalClass : Equal_classes) {
            if (equalClass.getStates().size() == 1) {
                next_EqualClasses.add(equalClass);
                continue;
            }
            boolean flag = false;
            for (int i = 0; i < alphabet.length(); i++) {
                ArrayList<String> new_class = new ArrayList<>();
                for (String state : equalClass.getStates()) {
                    for (Map.Entry<String, String> entry : automath.entrySet()) {
                        if (entry.getKey().contains(state) && entry.getKey().substring(4, 5).equals(String.valueOf(alphabet.charAt(i)))) {
                            if (!equalClass.getStates().contains(entry.getValue())) {
                                new_class.add(state);
                            }
                            break;
                        }
                    }
                }
                if ((new_class.isEmpty() && flag) || new_class.size() == equalClass.getStates().size()) {
                    next_EqualClasses.add(equalClass);
                    continue;
                }
                if (new_class.isEmpty()) {
                    flag = true;
                    continue;
                }
                EqualClass newClass1 = new EqualClass(new_class, "C" + (Equal_classes.size() + 1));
                ArrayList<String> buff = new ArrayList<>();
                for (String state : equalClass.getStates()) {
                    if (!new_class.contains(state)) buff.add(state);
                }
                boolean add_flag1 = false, add_flag2 = false;
                EqualClass newClass2 = new EqualClass(buff, equalClass.getName());
                for (EqualClass eclass : next_EqualClasses) {
                    if (eclass.equals(newClass2)) add_flag2 = true;
                    if (eclass.equals(newClass1)) add_flag1 = true;
                }
                if (!add_flag2) next_EqualClasses.add(newClass2);
                if (!add_flag1) next_EqualClasses.add(newClass1);
            }
        }
        if (Equal_classes.equals(next_EqualClasses)) return true;
        else {
            Equal_classes.clear();
            Equal_classes.addAll(next_EqualClasses);
            return false;
        }
    }
}
