import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TreeMultiSetTest {

    @Test
    public void testIsEmptyTrue() {
        TreeMultiSet b = new TreeMultiSet();
        assertTrue(b.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        TreeMultiSet b = new TreeMultiSet();
        b.add(5);
        assertFalse(b.isEmpty());
    }

    @Test
    public void testInsertEmpty() {
        TreeMultiSet b = new TreeMultiSet();
        b.add(4);
        assertTrue(b.contains(4));
    }

    @Test
    public void testInsertNonEmpty() {
        TreeMultiSet b = new TreeMultiSet();
        b.add(10);
        b.add(4);
        assertTrue(b.contains(4));
    }

    @Test
    public void testRemoveRoot() {
        TreeMultiSet b = new TreeMultiSet();
        b.add(10);
        b.remove(10);
        assertFalse(b.contains(10));
        assertTrue(b.isEmpty());
    }

    @Test
    public void testRemoveNonRoot() {
        TreeMultiSet b = new TreeMultiSet();
        b.add(10);
        b.add(4);
        b.remove(4);
        assertFalse(b.contains(4));
    }
    @Test
    public void testSizeEmpty() {
        TreeMultiSet b = new TreeMultiSet();
        assertEquals(0, b.size());
    }

    @Test
    public void testSizeOne() {
        TreeMultiSet b = new TreeMultiSet();
        b.add(10);
        assertEquals(1, b.size());
    }

    @Test
    public void testSizeNonEmpty() {
        TreeMultiSet b = new TreeMultiSet();
        b.add(10);
        b.add(4);
        b.add(12);
        b.add(13);
        assertEquals(4, b.size());
    }

    @Test
    public void testCountEmpty() {
        TreeMultiSet b = new TreeMultiSet();
        assertEquals(0, b.count(10));
    }

    @Test
    public void testCountOne() {
        TreeMultiSet b = new TreeMultiSet();
        b.add(11);
        assertEquals(1, b.count(11));
    }

    @Test
    public void testCountNonEmpty() {
        TreeMultiSet b = new TreeMultiSet();
        b.add(10);
        b.add(10);
        b.add(12);
        b.add(10);
        b.add(4);
        b.add(10);
        assertEquals(4, b.count(10));
    }


}