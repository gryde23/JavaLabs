public class PersonHashTable extends HashTable<Person,String> {
    public PersonHashTable(int tableSize, HashFunction<String> hashFunction){
        super(tableSize, hashFunction);
    }

    @Override
    public String getKey(Person element) {
        return element.name() + element.age() + element.surname();
    }
}
