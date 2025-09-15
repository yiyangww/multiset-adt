import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListMultiSetTest {

    @Test
    public void testIsEmptyTrue() {
        LinkedListMultiSet b = new LinkedListMultiSet();
        assertTrue(b.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        LinkedListMultiSet b = new LinkedListMultiSet();
        b.add(5);
        assertFalse(b.isEmpty());
    }

    @Test
    public void testInsertEmpty() {
        LinkedListMultiSet b = new LinkedListMultiSet();
        b.add(4);
        assertTrue(b.contains(4));
    }

    @Test
    public void testInsertNonEmpty() {
        LinkedListMultiSet b = new LinkedListMultiSet();
        b.add(10);
        b.add(4);
        assertTrue(b.contains(4));
    }

    @Test
    public void testRemoveRoot() {
        LinkedListMultiSet b = new LinkedListMultiSet();
        b.add(10);
        b.remove(10);
        assertFalse(b.contains(10));
        assertTrue(b.isEmpty());
    }

    @Test
    public void testRemoveNonRoot() {
        LinkedListMultiSet b = new LinkedListMultiSet();
        b.add(10);
        b.add(4);
        b.remove(4);
        assertFalse(b.contains(4));
    }
    @Test
    public void testSizeEmpty() {
        LinkedListMultiSet b = new LinkedListMultiSet();
        assertEquals(0, b.size());
    }

    @Test
    public void testSizeOne() {
        LinkedListMultiSet b = new LinkedListMultiSet();
        b.add(10);
        assertEquals(1, b.size());
    }

    @Test
    public void testSizeNonEmpty() {
        LinkedListMultiSet b = new LinkedListMultiSet();
        b.add(10);
        b.add(4);
        b.add(12);
        b.add(13);
        assertEquals(4, b.size());
    }

    @Test
    public void testCountEmpty() {
        LinkedListMultiSet b = new LinkedListMultiSet();
        assertEquals(0, b.count(10));
    }

    @Test
    public void testCountOne() {
        LinkedListMultiSet b = new LinkedListMultiSet();
        b.add(11);
        assertEquals(1, b.count(11));
    }

    @Test
    public void testCountNonEmpty() {
        LinkedListMultiSet b = new LinkedListMultiSet();
        b.add(10);
        b.add(10);
        b.add(12);
        b.add(10);
        b.add(4);
        b.add(10);
        assertEquals(4, b.count(10));
    }


}