import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {

    @Test
    public void testIsEmptyFalse() {
        BST b = new BST(5);
        assertFalse(b.isEmpty());
    }

    @Test
    public void testIsEmptyTrue() {
        BST b = new BST();
        assertTrue(b.isEmpty());
    }

    @Test
    public void testInsertEmpty() {
        BST b = new BST();
        b.insert(4);
        assertTrue(b.contains(4));
    }

    @Test
    public void testInsertNonEmpty() {
        BST b = new BST(10);
        b.insert(4);
        assertTrue(b.contains(4));
    }

    @Test
    public void testHeightEmpty() {
        BST b = new BST();
        assertEquals(0, b.height());
    }

    @Test
    public void testHeightOne() {
        BST b = new BST(10);
        assertEquals(1, b.height());
    }

    @Test
    public void testHeightNonEmpty() {
        BST b = new BST(10);
        b.insert(4);
        b.insert(12);
        b.insert(13);
        assertEquals(3, b.height());
    }

    @Test
    public void testHeightBalanced() {
        BST b = new BST(10);
        b.insert(4);
        b.insert(1);
        b.insert(5);
        b.insert(14);
        b.insert(11);
        b.insert(15);
        assertEquals(3, b.height());
    }
    @Test
    public void testHeightUnbalanced() {
        BST b = new BST(10);
        b.insert(11);
        b.insert(12);
        b.insert(13);
        b.insert(14);
        b.insert(15);
        b.insert(16);
        assertEquals(7, b.height());
    }

    @Test
    public void testCountEmpty() {
        BST b = new BST();
        assertEquals(0, b.count(10));
    }

    @Test
    public void testCountNonEmpty() {
        BST b = new BST(10);
        b.insert(10);
        b.insert(12);
        b.insert(10);
        b.insert(4);
        b.insert(10);
        assertEquals(4, b.count(10));
    }

    @Test
    public void testCountOne() {
        BST b = new BST(11);
        assertEquals(1, b.count(11));
    }

    @Test
    public void testCountAllDuplicates() {
        BST b = new BST(15);
        b.insert(15);
        b.insert(15);
        b.insert(15);
        b.insert(15);
        b.insert(15);
        b.insert(15);
        assertEquals(7, b.count(15));
    }

    @Test
    public void testGetSizeEmpty() {
        BST b = new BST();
        assertEquals(0, b.getSize());
    }

    @Test
    public void testGetSizeOne() {
        BST b = new BST(10);
        assertEquals(1, b.getSize());
    }

    @Test
    public void testGetSizeNonEmpty() {
        BST b = new BST(10);
        b.insert(4);
        b.insert(12);
        b.insert(13);
        assertEquals(4, b.getSize());
    }

    @Test
    public void testDeleteRoot() {
        BST b = new BST(10);
        b.delete(10);
        assertFalse(b.contains(10));
        assertTrue(b.isEmpty());
    }

    @Test
    public void testDeleteNonRoot() {
        BST b = new BST(10);
        b.insert(4);
        b.delete(4);
        assertFalse(b.contains(4));
    }

    @Test
    public void testDeleteRootWithSubtrees() {
        BST b = new BST(10);
        b.insert(4);
        b.insert(1);
        b.delete(10);
        assertTrue(b.contains(4));
        assertTrue(b.contains(1));
        assertFalse(b.contains(10));
    }

    @Test
    public void testDeleteAll() {
        BST b = new BST(10);
        b.insert(4);
        b.insert(1);
        b.delete(1);
        b.delete(10);
        b.delete(4);
        assertFalse(b.contains(4));
        assertFalse(b.contains(1));
        assertFalse(b.contains(10));
    }


}