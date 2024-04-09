import java.util.*;

public class MinAutomath {
    private final String alphabet;
    private final ArrayList<String> end_states;
    private final Map<String,String> min_automath = new LinkedHashMap<>();
    private final Set<EqualClass> Equal_classes;
    private Set<String> States = new LinkedHashSet<>();
    private final Set<String> min_ends = new LinkedHashSet<>();

    public MinAutomath(String alphabet, ArrayList<String> end_states, Set<EqualClass> Equal_classes){
        this.alphabet = alphabet;
        this.end_states = end_states;
        this.Equal_classes = Equal_classes;
        for (EqualClass eclass: Equal_classes) {
            States.add(eclass.getStates().getFirst());
        }
        List<String> list = new ArrayList<>(States);
        Collections.sort(list);
        States = new LinkedHashSet<>(list);
    }
    public void buildMinAtm(Map<String,String> automath){
        for (String state: end_states){
            for (EqualClass eclass: Equal_classes){
                if (eclass.getStates().contains(state)) min_ends.add(eclass.getStates().getFirst());
            }
        }
        for (int i = 0; i < alphabet.length(); i++){
            for (String state: States){
                for (Map.Entry<String,String> entry: automath.entrySet()){
                    if (!entry.getKey().contains(state) || !entry.getKey().substring(4,5).equals(String.valueOf(alphabet.charAt(i)))) continue;
                    if (!States.contains(entry.getValue())) {
                        String val = searchState(entry.getValue());
                        min_automath.put(state + ";" + alphabet.charAt(i), val);
                        break;
                    }
                    min_automath.put(state + ";" + alphabet.charAt(i), entry.getValue());
                }
            }
        }
    }
    public String searchState(String state){
        String res = "";
        for (EqualClass eclass: Equal_classes){
            if (eclass.getStates().contains(state)) res = eclass.getStates().getFirst();
        }
        return res;
    }
    public void print(){
        System.out.println();
        System.out.print(" ".repeat(10));
        for (int i = 0; i < alphabet.length(); i++) {
            System.out.print(" |" + " ".repeat(5) + alphabet.charAt(i) + " ".repeat(5));
        }
        System.out.println("|\n" + "-".repeat(12 * (alphabet.length() + 1)));
        for (String q : States) {
            if (q.equals("q0")) System.out.printf("-> %4s[%s]|", " ", q);
            else if (min_ends.contains(q)) System.out.printf("<- %4s[%s]|", " ", q);
            else System.out.printf("%7s[%s]|", " ",q);
            for (int i = 0; i < alphabet.length(); i++) {
                for (Map.Entry<String, String> entry : min_automath.entrySet()) {
                    String key = entry.getKey().substring(0, 2);
                    String simb = entry.getKey().substring( 3);
                    if (!q.equals(key) || !simb.equals(String.valueOf(alphabet.charAt(i)))) continue;
                    String value = entry.getValue();
                    System.out.printf("%8s[%s]|", " ",value);
                    break;
                }
            }
            System.out.println();
        }
    }
}
