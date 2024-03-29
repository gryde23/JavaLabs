public class GPairBag<T> extends Bag{
    public GPairBag(int max) {
        super(max);
    }
    public void addPair(Pair<T,T> pair){
        super.addElement(pair);
    }
}
