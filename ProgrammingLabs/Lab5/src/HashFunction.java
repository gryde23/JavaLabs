public abstract class HashFunction<K> {
    protected int tableSize;
    public HashFunction(int tableSize){
        this.tableSize = tableSize;
    }
    public abstract int hash(K s);
}
