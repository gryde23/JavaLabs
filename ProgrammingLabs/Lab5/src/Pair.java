public class Pair<F, S> {
    private F first;
    private S second;

    public Pair(F first, S second){
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }
    public S getSecond() {
        return second;
    }
    @Override
    public String toString() {
        return "(" + first + ";" + second + ")";
    }

    public void setFirst(F first) {
        this.first = first;
    }
    public void setSecond(S second) {
        this.second = second;
    }
    public static <P, V> Pair make_pair(P first, V second){
        return new Pair(first, second);
    }
}
