import java.util.LinkedList;

public class LinkedListMultiSet<T> implements MultiSet<T> {
    private LinkedList<T> list;

    public LinkedListMultiSet() {
        list = new LinkedList<>();
    }

    @Override
    public boolean add(T item) {
        return list.add(item);
    }

    @Override
    public void remove(T item) {
        list.remove(item);
    }

    @Override
    public boolean contains(T item) {
        return list.contains(item);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int count(T item) {
        int count = 0;
        for (T t : list) {
            if (t.equals(item)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int size() {
        return list.size();
    }
}
