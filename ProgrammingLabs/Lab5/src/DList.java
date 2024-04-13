import java.util.ArrayList;
import java.util.List;

public class DList<T1, T2> {
    private final List<T1> list1;
    private final List<List<T2>> list2;

    public DList(){
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
    }
    public void add(T1 val, List<T2> list){
        list1.add(val);
        list2.add(list);
    }
    public Pair<T1, List<T2>> getByInd(int i){
        return new Pair<>(list1.get(i), list2.get(i));
    }
    public void removeByInd(int i){
        list1.remove(i);
        list2.remove(i);
    }

    @Override
    public String toString() {
        return "DList{" +
                "\nlist1=" + list1 +
                "\nlist2=" + list2 +
                '}';
    }

    public Pair<T1, List<T2>> getByKey(T1 value){
        int ind = list1.indexOf(value);
        return new Pair<>(value, list2.get(ind));
    }
    public void removeByKey(T1 value){
        list2.remove(list1.indexOf(value));
        list1.remove(value);
    }
}
