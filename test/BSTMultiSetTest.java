import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BSTMultiSetTest {

    @Test
    public void testIsEmptyTrue() {
        BSTMultiSet b = new BSTMultiSet();
        assertTrue(b.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        BSTMultiSet b = new BSTMultiSet();
        b.add(5);
        assertFalse(b.isEmpty());
    }

    @Test
    public void testInsertEmpty() {
        BSTMultiSet b = new BSTMultiSet();
        b.add(4);
        assertTrue(b.contains(4));
    }

    @Test
    public void testInsertNonEmpty() {
        BSTMultiSet b = new BSTMultiSet();
        b.add(10);
        b.add(4);
        assertTrue(b.contains(4));
    }

    @Test
    public void testRemoveRoot() {
        BSTMultiSet b = new BSTMultiSet();
        b.add(10);
        b.remove(10);
        assertFalse(b.contains(10));
        assertTrue(b.isEmpty());
    }

    @Test
    public void testRemoveNonRoot() {
        BSTMultiSet b = new BSTMultiSet();
        b.add(10);
        b.add(4);
        b.remove(4);
        assertFalse(b.contains(4));
    }
    @Test
    public void testSizeEmpty() {
        BSTMultiSet b = new BSTMultiSet();
        assertEquals(0, b.size());
    }

    @Test
    public void testSizeOne() {
        BSTMultiSet b = new BSTMultiSet();
        b.add(10);
        assertEquals(1, b.size());
    }

    @Test
    public void testSizeNonEmpty() {
        BSTMultiSet b = new BSTMultiSet();
        b.add(10);
        b.add(4);
        b.add(12);
        b.add(13);
        assertEquals(4, b.size());
    }

    @Test
    public void testCountEmpty() {
        BSTMultiSet b = new BSTMultiSet();
        assertEquals(0, b.count(10));
    }

    @Test
    public void testCountOne() {
        BSTMultiSet b = new BSTMultiSet();
        b.add(11);
        assertEquals(1, b.count(11));
    }

    @Test
    public void testCountNonEmpty() {
        BSTMultiSet b = new BSTMultiSet();
        b.add(10);
        b.add(10);
        b.add(12);
        b.add(10);
        b.add(4);
        b.add(10);
        assertEquals(4, b.count(10));
    }


}