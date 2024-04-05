public class Pair {
    private Integer first;
    private Integer second;

    public Pair(Integer first, Integer second){
        this.first = first;
        this.second = second;
    }

    public Integer getFirst() {
        return first;
    }
    public Integer getSecond() {
        return second;
    }
    @Override
    public String toString() {
        return "(" + first + ";" + second + ")";
    }

    public void setFirst(Integer first) {
        this.first = first;
    }
    public void setSecond(Integer second) {
        this.second = second;
    }
}
