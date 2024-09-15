public interface MultiSet<T> {
    boolean add(T item);
    void remove(T item);
    boolean contains(T item);
    boolean isEmpty();
    int count(T item);
    int size();
}
