import java.util.ArrayList;
import java.util.Random;

public class Bag {
    private final Random random = new Random();
    private final int capacity;
    protected ArrayList<Object> bag;
    public Bag(int max) {
        this.capacity = max;
        this.bag = new ArrayList<>(capacity);
    }

    public <T> void addElement(T elem){
        if (bag.size() >= capacity) {
            System.out.println("Мешок полон");
            return;
        }
        if (bag.isEmpty()) {
            bag.add(elem);
            return;
        }
        int index = random.nextInt(bag.size());
        bag.add(index, elem);
    }
    public Object removeElem(){
        int index = random.nextInt(bag.size());
        Object buff = bag.get(index);
        bag.remove(index);
        return buff;
    }
    public Object getElem(){
        int index = random.nextInt(bag.size());
        return bag.get(index);
    }
    public int getBagSize(){
        return bag.size();
    }

    @Override
    public String toString() {
        return bag.toString();
    }
}
