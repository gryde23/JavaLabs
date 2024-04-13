import java.util.ArrayList;
import java.util.List;

public abstract class HashTable<T, K> {
    private final List<T>[] table;
    private final HashFunction<K> hashFunction;

    public HashTable(int tableSize, HashFunction<K> hashFunction){
        this.table = new ArrayList[tableSize];
        for (int i = 0; i < tableSize; i++){
            this.table[i] = new ArrayList<T>();
        }
        this.hashFunction = hashFunction;
    }

    public void add(T element) {
        K key = getKey(element);
        int index = hashFunction.hash(key);
        table[index].add(element);
    }

    public List<T> get(K key) {
        int index = hashFunction.hash(key);
        return table[index];
    }

    public abstract K getKey(T element);
}
