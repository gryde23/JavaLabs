import java.util.ArrayList;

public class Gram {
    ArrayList<Pair<String, String>> rules;
    ArrayList<String> terminals;
    ArrayList<String> nonterminals;

    public Gram(ArrayList<Pair<String, String>> rules, ArrayList<String> terminals, ArrayList<String> nonterminals){
        this.rules = rules;
        this.nonterminals = nonterminals;
        this.terminals = terminals;
    }

    public boolean leftLine(){
        for (Pair<String,String> rule: rules){
            if (rule.getFirst().length() > 1 ||
                    terminals.contains(rule.getFirst())) return false;
            String rightPart = rule.getSecond();
            if (rightPart.equals("E")) continue;
            int count = 0;
            for (String nonterm: nonterminals){
                for (int i = 0; i < rightPart.length(); i++){
                    if (String.valueOf(rightPart.charAt(i)).equals(nonterm)) count++;
                }
                if ((rightPart.contains(nonterm) && !nonterminals.contains(String.valueOf(rightPart.charAt(0)))) || count > 1) return false;
            }
        }
        return true;
    }

    public boolean rightLine(){
        for (Pair<String,String> rule: rules){
            if (rule.getFirst().length() > 1 ||
                    terminals.contains(rule.getFirst())) return false;
            String rightPart = rule.getSecond();
            if (rightPart.equals("E")) continue;
            int count = 0;
            for (String nonterm: nonterminals){
                for (int i = 0; i < rightPart.length(); i++){
                    if (String.valueOf(rightPart.charAt(i)).equals(nonterm)) count++;
                }
                if ((rightPart.contains(nonterm) && !nonterminals.contains(String.valueOf(rightPart.charAt(rightPart.length() - 1)))) || count > 1) return false;
            }
        }
        return true;
    }

    public boolean contextFree(){
        for (Pair<String,String> rule: rules){
            if (rule.getFirst().length() > 1 || terminals.contains(rule.getFirst())) return false;
        }
        return true;
    }
    public boolean contextDependent(){
        for (Pair<String,String> rule: rules){
            if (rule.getFirst().equals("S") && rule.getSecond().equals("E")) {
                for (Pair<String,String> r: rules){
                    if (r.getSecond().contains("S")) return false;
                }
            }
            String leftPart = rule.getFirst();
            boolean N = false;
            for (String nonterm: nonterminals){
                if (leftPart.contains(nonterm)) {
                    N = true;
                    break;
                }
            }
            if (!N) return false;
            String rightPart = rule.getSecond();
            if (rightPart.equals("E")) return false;
            String psi1,psi2;
            int i = 0;
            while(!nonterminals.contains(String.valueOf(leftPart.charAt(i)))){
                i++;
            }
            if (i > 0) psi1 = leftPart.substring(0, i);
            else psi1 = "";
            int j = leftPart.length() - 1;
            while(!nonterminals.contains(String.valueOf(leftPart.charAt(j)))){
                j--;
            }
            if (j < leftPart.length() - 1) psi2 = leftPart.substring(j);
            else psi2 = "";
//            if (!rightPart.substring(0, i).equals(psi1) || !rightPart.substring(rightPart.length() - j).equals(psi2)) return false;
            if (leftPart.substring(i,j + 1).length() > 1) return false;
            if (!rightPart.contains(psi1) || !rightPart.contains(psi2)) return false;
            if (i == j && !psi1.isEmpty() && !psi2.isEmpty()) return false;
        }
        return true;
    }
    public boolean unshortenable(){
        for (Pair<String,String> rule: rules){
            if (rule.getFirst().equals("S") && rule.getSecond().equals("E")) {
                for (Pair<String,String> r: rules){
                    if (r.getSecond().contains("S")) return false;
                }
            }
            if (rule.getFirst().length() > rule.getSecond().length()) return false;
        }
        return true;
    }
}
