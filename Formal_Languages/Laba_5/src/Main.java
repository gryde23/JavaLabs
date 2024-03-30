import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String alphabet = Alphabet();
        System.out.print("Введите количество состояний: ");
        int amountOfStates = scanner.nextInt();
        ArrayList<String> States = new ArrayList<>(amountOfStates);
        for (int i = 0; i < amountOfStates; i++) {
            States.add("q" + i);
        }
        scanner.nextLine();
        System.out.print("Начальные состояния: ");
        String buff = scanner.nextLine();
        ArrayList<String> begin = new ArrayList<>(Arrays.asList(buff.split("\\s")));
        boolean flag = true;
        while (flag) {
            for (String state : begin) {
                if (Objects.equals(state, "-")) break;
                if (!States.contains(state)) {
                    System.out.print("Некорректный ввод! Повторите попытку: ");
                    buff = scanner.nextLine();
                    begin = new ArrayList<>(Arrays.asList(buff.split("\\s")));
                    flag = true;
                    break;
                }
                flag = false;
            }
        }
        System.out.print("Конечные состояния: ");
        buff = scanner.nextLine();
        ArrayList<String> end = new ArrayList<>(Arrays.asList(buff.split("\\s")));
        flag = true;
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
        Map<String, String> machine = Machine(States, alphabet);
        System.out.println(machine);
        showMachine(machine, States, alphabet, begin, end);
        ArrayList<String> buff_begin = new ArrayList<>();
        for (String q: begin) {
            for (Map.Entry<String, String> q_check: machine.entrySet()){
                if(q_check.getKey().contains(q) && q_check.getKey().contains("E")) {
                    String[] val = q_check.getValue().split(",");
                    buff_begin.addAll(Arrays.asList(val));
                }
            }
        }
        begin.addAll(buff_begin);
        Map<String, String> Etransitions = Etransitions(machine, States);
        Map<String, String> machineS = machineS(Etransitions, machine, alphabet);
        System.out.println(machineS);
        Set<String> S_begin = new LinkedHashSet<>();
        Set<String> S_end = new LinkedHashSet<>();
        for (Map.Entry<String, String> S : Etransitions.entrySet()) {
            for (String q : begin) {
                if (S.getValue().contains(q)) S_begin.add(S.getKey());
            }
            for (String q : end) {
                if (S.getValue().contains(q)) S_end.add(S.getKey());
            }
        }
        begin = new ArrayList<>(S_begin);
        end = new ArrayList<>(S_end);
        showMachineS(machineS, Etransitions, alphabet, begin, end);
        Map<String, String> machineP = machineP(machineS, alphabet, begin, end);
        System.out.println(machineP);
    }

    public static String Alphabet() {
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

    public static Map<String, String> Machine(ArrayList<String> States, String alphabet) {
        Map<String, String> machine = new LinkedHashMap<>();
        String key = "";
        StringBuilder buff = new StringBuilder();
        String b;
        for (String state : States) {
            for (int i = 0; i < alphabet.length() + 1; i++) {
                if (i == alphabet.length()) {
                    key = "(" + state + ";E)";
                } else key = "(" + state + ";" + alphabet.charAt(i) + ")";
                System.out.print(key + "-> ");
                buff = new StringBuilder(scanner.nextLine());
                ArrayList<String> res = new ArrayList<>(Arrays.asList(buff.toString().split("\\s")));
                buff = new StringBuilder();
                boolean flag = true;
                while (flag) {
                    for (String stat : res) {
                        if (Objects.equals(state, "-")) break;
                        if (!States.contains(state)) {
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
        return machine;
    }

    public static void showMachine(Map<String, String> machine, ArrayList<String> States, String alphabet, ArrayList<String> begin, ArrayList<String> end) {
        System.out.print(" ".repeat(10));
        for (int i = 0; i < alphabet.length(); i++) {
            System.out.print("|" + " ".repeat(5) + alphabet.charAt(i) + " ".repeat(5));
        }
        System.out.print("|" + " ".repeat(5) + "E" + " ".repeat(5) + "|\n");
        System.out.println("-".repeat(12 * (alphabet.length()) + 23));
        for (String state : States) {
            if (begin.contains(state)) System.out.printf("-> %7s|", state);
            else if (end.contains(state)) {
                System.out.printf("<- %7s|", state);
            } else System.out.printf("%10s|", state);
            for (int i = 0; i < alphabet.length() + 1; i++) {
                for (Map.Entry<String, String> entry : machine.entrySet()) {
                    String key = entry.getKey();
                    if (i == alphabet.length()) {
                        if (!key.equals("(" + state + ";E)")) continue;
                    } else {
                        if (!key.equals("(" + state + ";" + alphabet.charAt(i) + ")")) continue;
                    }
                    String value = entry.getValue();
                    System.out.printf("%11s|", value);
                }
            }
            System.out.println();
        }
    }
    public static Map<String, String> Etransitions(Map<String, String> machine, ArrayList<String> States) {
        Map<String, String> Etransitions = new LinkedHashMap<>();
        int ind = 0;
        for (String state : States) {
            for (Map.Entry<String, String> entry : machine.entrySet()) {
                if (!entry.getKey().equals("(" + state + ";E)")) continue;
                if (!entry.getValue().equals("-")) Etransitions.put("S" + ind, state + "," + entry.getValue());
                else Etransitions.put("S" + ind, state);
            }
            ind++;
        }
        for (Map.Entry<String, String> entry : Etransitions.entrySet()) {
            List<String> val = new ArrayList<>(List.of(entry.getValue().split(",")));
            Collections.sort(val);
            StringBuilder buff = new StringBuilder();
            for(String v: val){
                buff.append(v).append(",");
            }
            buff = new StringBuilder(buff.substring(0, buff.length() - 1));
            Etransitions.put(entry.getKey(), buff.toString());
            System.out.println("ε-замыкание " + entry.getKey() + " = {" + entry.getValue() + "}");
        }
        return Etransitions;
    }

    public static Map<String, String> machineS(Map<String, String> Etransitions, Map<String, String> machine, String alphabet) {
        Map<String, String> machineS = new LinkedHashMap<>();
        for (Map.Entry<String, String> S : Etransitions.entrySet()) {
            ArrayList<String> q_States = new ArrayList<>(List.of(S.getValue().split(",")));
            for(int i = 0; i < alphabet.length(); i++) {
                Set <String> S_states = new LinkedHashSet<>();
                Set<String> q_Value = new LinkedHashSet<>();
                Set<String> q_E = new LinkedHashSet<>();
                for (String q: q_States){
                    for (Map.Entry<String, String> mach: machine.entrySet()){
                        String key = mach.getKey().substring(1,3);
                        String simb = mach.getKey().substring(4,5);
                        if (q.equals(key) && simb.equals(String.valueOf(alphabet.charAt(i))) && !mach.getValue().equals("-")){
                            String[] value = mach.getValue().split(",");
                            q_Value.addAll(List.of(value));
                        } else if (q.equals(key) && simb.equals("E") && !mach.getValue().equals("-")) {
                            String[] value = mach.getValue().split(",");
                            q_E.addAll(List.of(value));
                        }
                    }
                }
                if (q_Value.isEmpty()) {
                    machineS.put("(" + S.getKey() + ";" + alphabet.charAt(i) + ")", "-");
                    continue;
                }
                ArrayList<String> q_buff = new ArrayList<>();
                for(String q: q_Value){
                    for(Map.Entry<String, String> mach: machine.entrySet()) {
                        String key = mach.getKey().substring(1,3);
                        String simb = mach.getKey().substring(4,5);
                        if(q.equals(key) && simb.equals("E") && !mach.getValue().equals("-")) {
                            String[] value = mach.getValue().split(",");
                            q_buff.addAll(List.of(value));
                        }
                    }
                }
                q_Value.addAll(q_buff);
                for(String q: q_E){
                    for(Map.Entry<String, String> mach:machine.entrySet()){
                        String key = mach.getKey().substring(1,3);
                        String simb = mach.getKey().substring(4,5);
                        if(q.equals(key) && simb.equals(String.valueOf(alphabet.charAt(i))) && !mach.getValue().equals("-")){
                            String[] value = mach.getValue().split(",");
                            q_Value.addAll(List.of(value));
                        }
                    }
                }
                List<String> list = new ArrayList<>(q_Value);
                Collections.sort(list);
                q_Value = new LinkedHashSet<>(list);
                StringBuilder q_Str = new StringBuilder();
                for(String q: q_Value){
                    q_Str.append(q).append(",");
                }
                q_Str = new StringBuilder(q_Str.substring(0, q_Str.length() - 1));
                for(Map.Entry<String,String> S_check: Etransitions.entrySet()){
                    if(S_check.getValue().contentEquals(q_Str)) {
                        S_states.add(S_check.getKey());
                        continue;
                    }
                    StringBuilder buff = new StringBuilder();
                    String[] value = q_Str.toString().split(",");
                    for(String q: value){
                        if (S_check.getValue().contains(q)) {
                            buff.append(q).append(",");
                        }
                    }
                    if (buff.isEmpty()) continue;
                    buff = new StringBuilder(buff.substring(0, buff.length() - 1));
                    if (S_check.getValue().contentEquals(buff)) {
                        S_states.add(S_check.getKey());
                    }
                }
                if (S_states.isEmpty()) machineS.put("(" + S.getKey() + ";" + alphabet.charAt(i) + ")", "-");
                else {
                    StringBuilder to_str = new StringBuilder();
                    for(String S_state: S_states){
                        to_str.append(S_state).append(",");
                    }
                    to_str = new StringBuilder(to_str.substring(0, to_str.length() - 1));
                    machineS.put("(" + S.getKey() + ";" + alphabet.charAt(i) + ")", to_str.toString());
                }
            }
        }
        return machineS;
    }
    public static void showMachineS(Map<String, String> machine, Map<String,String> Etransition, String alphabet, ArrayList<String> begin, ArrayList<String> end) {
        System.out.print(" ".repeat(10));
        for (int i = 0; i < alphabet.length(); i++) {
            System.out.print("|" + " ".repeat(5) + alphabet.charAt(i) + " ".repeat(5));
        }
        System.out.println("|\n" + "-".repeat(12 * (alphabet.length() + 1)));
        for (Map.Entry<String, String> S: Etransition.entrySet()) {
            if (begin.contains(S.getKey())) System.out.printf("-> %7s|", S.getKey());
            else if (end.contains(S.getKey())) {
                System.out.printf("<- %7s|", S.getKey());
            } else System.out.printf("%10s|", S.getKey());
            for (int i = 0; i < alphabet.length(); i++) {
                for (Map.Entry<String, String> entry : machine.entrySet()) {
                    String key = entry.getKey().substring(1,3);
                    String simb = entry.getKey().substring(4,5);
                    if (!S.getKey().equals(key) || !simb.equals(String.valueOf(alphabet.charAt(i)))) continue;
                    String value = entry.getValue();
                    System.out.printf("%11s|", value);
                }
            }
            System.out.println();
        }
    }
    public static Map<String,String> machineP(Map<String,String> machineS, String alphabet, ArrayList<String> begin, ArrayList<String> end) {
        Map<String,String> machineP = new LinkedHashMap<>();
        Map<String,String> P = new LinkedHashMap<>();
        StringBuilder buff = new StringBuilder();
        for(String s: begin){
            buff.append(s).append(",");
        }
        buff = new StringBuilder(buff.substring(0, buff.length() - 1));
        P.put("p0", buff.toString());
        for (String S: begin){
            for(int i = 0; i< alphabet.length(); i++){
                for (Map.Entry<String,String> S_check: machineS.entrySet()){
                    String simb = S_check.getKey().substring(4,5);
                    if (!S_check.getKey().contains(S) || !simb.equals(String.valueOf(alphabet.charAt(i))) || S_check.getValue().equals("-")) continue;
                    if (P.containsValue(S_check.getValue())) {
                        for (Map.Entry<String,String> p: P.entrySet()){
                            if (p.getValue().equals(S_check.getValue()))machineP.put("(p0;" + alphabet.charAt(i) + ")", p.getKey());
                        }
                    }
                    else {
                        P.put("p" + P.size(), S_check.getValue());
                        machineP.put("(p0;" + alphabet.charAt(i) + ")", "p" + (P.size() - 1));
                    }
                }
            }
        }
        add:
        while(true){
            for (Map.Entry<String,String> P_vertex: P.entrySet()){
                if (P_vertex.getKey().equals("p0")) continue;
                String[] Val = P_vertex.getValue().split(",");
                for (int i = 0; i < alphabet.length(); i++) {
                    Set<String> S_states = new LinkedHashSet<>();
                    for (String S: Val) {
                        for(Map.Entry<String,String> S_check: machineS.entrySet()){
                            String simb = S_check.getKey().substring(4,5);
                            if (!S_check.getKey().contains(S) || !simb.equals(String.valueOf(alphabet.charAt(i))) || S_check.getValue().equals("-")) continue;
                            S_states.add(S_check.getValue());
                        }
                    }
                    buff = new StringBuilder();
                    for (String S_state: S_states){
                        buff.append(S_state).append(",");
                    }
                    if (buff.isEmpty()) {
                        machineP.put("(" + P_vertex.getKey() + ";" + alphabet.charAt(i) + ")", "-");
                        continue;
                    }
                    buff = new StringBuilder(buff.substring(0, buff.length() - 1));
                    if (P.containsValue(buff.toString())) {
                        for (Map.Entry<String,String> p: P.entrySet()){
                            if (p.getValue().contentEquals(buff)) machineP.put("(" + P_vertex.getKey() + ";" + alphabet.charAt(i) + ")", p.getKey());
                        }
                    }
                    else {
                        P.put("p" + P.size(), buff.toString());
                        machineP.put("(" + P_vertex.getKey() + ";" + alphabet.charAt(i) + ")", "p" + (P.size() - 1));
                        continue add;
                    }
                }
            }
            break;
        }
        System.out.println(P);
        return machineP;
    }
}