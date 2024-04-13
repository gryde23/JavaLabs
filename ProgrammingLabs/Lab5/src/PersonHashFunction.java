public class PersonHashFunction extends HashFunction<String>{
    public PersonHashFunction(int tableSize) {
        super(tableSize);
    }

    @Override
    public int hash(String s) {
        if (s == null) return 0;
        return Math.abs(s.hashCode() % tableSize);
    }
}
