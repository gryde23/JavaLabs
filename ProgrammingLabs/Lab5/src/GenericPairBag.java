import java.util.ArrayList;
import java.util.Random;

public class GenericPairBag<T> {
    private ArrayList<Pair<T,T>> pairs;

    public GenericPairBag(){
        pairs = new ArrayList<>();
    }

    public void addPair(Pair<T,T> pair){
        pairs.add(pair);
    }

    @Override
    public String toString() {
        return "GenericPairBag{" +
                "pairs=" + pairs +
                '}';
    }

    public Pair<T,T> getPair() {
        if (pairs.isEmpty()) {
            throw new IllegalStateException("Pair bag is empty");
        }
        Random random = new Random();
        int index = random.nextInt(pairs.size());
        return pairs.get(index);
    }
    public Pair<T,T> removePair(){
        Random random = new Random();
        int index = random.nextInt(pairs.size());
        Pair<T,T> buff = pairs.get(index);
        pairs.remove(index);
        return buff;
    }
}
