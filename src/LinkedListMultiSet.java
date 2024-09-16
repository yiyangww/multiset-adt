import java.util.LinkedList;

public class LinkedListMultiSet<T> implements MultiSet<T> {
    private Node<T> front;
    private int size;

    public LinkedListMultiSet() {
        this.front = null;
        this.size = 0;
    }

    @Override
    public boolean add(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = front;
        front = newNode;
        size++;
        return true;
    }

    @Override
    public void remove(T item) {
        Node<T> cur = front;
        Node<T> prev = null;
        while (cur != null) {
            if (cur.item.equals(item)) {
                size--;
                if (prev != null) {
                    prev.next = cur.next;
                } else { // first item
                    front = cur.next;
                }
                return;
            }
            prev = cur;
            cur = cur.next;
        }
    }

    @Override
    public boolean contains(T item) {
        Node<T> cur = front;
        while (cur != null) {
            if (cur.item.equals(item)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public int count(T item) {
        int numSeen = 0;
        Node<T> cur = front;
        while (cur != null) {
            if (cur.item.equals(item)) {
                numSeen++;
            }
            cur = cur.next;
        }
        return numSeen;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node<T> {
        T item;
        Node<T> next;

        Node(T item) {
            this.item = item;
            this.next = null;
        }
    }
}
