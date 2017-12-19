package ru.spbau.hw7MyTreeSet;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * A class that contains simple tests to all methods of MyTreeSet
 */
public class MyTreeSetImplementationTest {

    MyTreeSetImplementation<Integer> tree;

    /**
     * Initialize tree before each test
     */
    @Before
    public void initTree() {
        tree = new MyTreeSetImplementation<>();
    }

    /**
     * Simple test of adding elements
     */
    @Test
    public void testAdd() {
        tree.add(5);

        assertEquals(1, tree.size());

        tree.add(6);

        assertEquals(2, tree.size());

        tree.add(4);

        assertEquals(3, tree.size());

        tree.add(3);

        assertEquals(4, tree.size());
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(6));
        assertTrue(tree.contains(4));
        assertTrue(tree.contains(3));
    }

    /**
     * Simple test of containing elements in tree
     */
    @Test
    public void testContains() {
        tree.add(5);
        tree.add(6);
        tree.add(4);
        tree.add(3);

        assertTrue(tree.contains(5));
        assertTrue(tree.contains(6));
        assertTrue(tree.contains(4));
        assertTrue(tree.contains(3));
        assertFalse(tree.contains(8));
        assertFalse(tree.contains(0));
        assertFalse(tree.contains(-1));
    }

    /**
     * Simple test of iterating elements from small to big
     */
    @Test
    public void testIterator1() {
        tree.add(5);
        tree.add(6);
        tree.add(4);
        tree.add(3);

        Iterator<Integer> it = tree.iterator();

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(3));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(4));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(5));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(6));

        assertFalse(it.hasNext());
    }

    /**
     * Simple test of iterating elements from big to small
     */
    @Test
    public void testDescendingIterator1() {
        tree.add(5);
        tree.add(6);
        tree.add(4);
        tree.add(3);

        Iterator<Integer> it = tree.descendingIterator();

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(6));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(5));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(4));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(3));

        assertFalse(it.hasNext());
    }

    /**
     * Simple test of iterating elements from small to big
     */
    @Test
    public void testIterator2() {
        tree.add(5);
        tree.add(8);
        tree.add(7);
        tree.add(9);
        tree.add(3);
        tree.add(4);
        tree.add(2);

        Iterator<Integer> it = tree.iterator();

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(2));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(3));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(4));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(5));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(7));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(8));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(9));

        assertFalse(it.hasNext());
    }

    /**
     * Simple test of iterating elements from big to small
     */
    @Test
    public void testDescendingIterator2() {
        tree.add(5);
        tree.add(8);
        tree.add(7);
        tree.add(9);
        tree.add(3);
        tree.add(4);
        tree.add(2);

        Iterator<Integer> it = tree.descendingIterator();

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(9));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(8));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(7));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(5));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(4));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(3));

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(2));

        assertFalse(it.hasNext());
    }

    /**
     * Simple test of tree size
     */
    @Test
    public void testSize() {
        assertEquals(0, tree.size());

        tree.add(5);
        tree.add(8);
        tree.add(7);
        tree.add(9);
        tree.add(3);
        tree.add(4);
        tree.add(2);

        assertEquals(7, tree.size());
    }

    /**
     * Simple test of take first element
     */
    @Test
    public void testFirst() {
        assertNull(tree.first());

        tree.add(5);
        assertTrue(tree.first().equals(5));

        tree.add(8);
        assertTrue(tree.first().equals(5));

        tree.add(7);
        assertTrue(tree.first().equals(5));

        tree.add(9);
        assertTrue(tree.first().equals(5));

        tree.add(3);
        assertTrue(tree.first().equals(3));

        tree.add(4);
        assertTrue(tree.first().equals(3));

        tree.add(2);
        assertTrue(tree.first().equals(2));

    }

    /**
     * Simple test of take last element
     */
    @Test
    public void testLast() {
        assertNull(tree.last());

        tree.add(5);
        assertTrue(tree.last().equals(5));

        tree.add(8);
        assertTrue(tree.last().equals(8));

        tree.add(7);
        assertTrue(tree.last().equals(8));

        tree.add(9);
        assertTrue(tree.last().equals(9));

        tree.add(3);
        assertTrue(tree.last().equals(9));

        tree.add(4);
        assertTrue(tree.last().equals(9));

        tree.add(2);
        assertTrue(tree.last().equals(9));
    }

    /**
     * Simple test of removing root node
     */
    @Test
    public void testRemoveRoot() {
        tree.add(5);
        tree.add(8);
        tree.add(7);
        tree.add(9);
        tree.add(3);
        tree.add(4);
        tree.add(2);

        tree.remove(5);
        assertEquals(6, tree.size());

        Iterator<Integer> it = tree.iterator();

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(2));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(3));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(4));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(7));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(8));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(9));
        assertFalse(it.hasNext());
    }

    /**
     * Simple test of removing root without branches
     */
    @Test
    public void testRemoveRootSingle() {
        tree.add(5);

        tree.remove(5);
        assertEquals(0, tree.size());

        Iterator<Integer> it = tree.iterator();

        assertFalse(it.hasNext());
    }

    /**
     * Simple test of removing root with only left branch
     */
    @Test
    public void testRemoveRootLeft() {
        tree.add(5);
        tree.add(3);
        tree.add(4);
        tree.add(2);
        tree.remove(5);
        assertEquals(3, tree.size());

        Iterator<Integer> it = tree.iterator();

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(2));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(3));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(4));
        assertFalse(it.hasNext());
    }

    /**
     * Simple test of removing root with only right branch
     */
    @Test
    public void testRemoveRootRight() {
        tree.add(5);
        tree.add(7);
        tree.add(6);
        tree.add(8);
        tree.remove(5);
        assertEquals(3, tree.size());

        Iterator<Integer> it = tree.iterator();

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(6));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(7));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(8));
        assertFalse(it.hasNext());
    }

    /**
     * Simple test of removing root
     */
    @Test
    public void testRemoveRootBoth() {
        tree.add(5);
        tree.add(7);
        tree.add(6);
        tree.add(8);
        tree.add(3);
        tree.add(4);
        tree.add(2);
        tree.remove(5);
        assertEquals(6, tree.size());

        Iterator<Integer> it = tree.iterator();

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(2));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(3));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(4));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(6));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(7));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(8));
        assertFalse(it.hasNext());
    }

    /**
     * Simple test of removing from empty tree
     */
    @Test
    public void testRemoveFromEmpty() {
        tree.remove(5);
        assertEquals(0, tree.size());

        Iterator<Integer> it = tree.iterator();

        assertFalse(it.hasNext());
    }

    /**
     * Simple test of removing leaf
     */
    @Test
    public void testRemoveLeaf() {
        tree.add(5);
        tree.add(4);
        tree.remove(4);
        assertEquals(1, tree.size());

        Iterator<Integer> it = tree.iterator();

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(5));
        assertFalse(it.hasNext());
    }

    /**
     * Simple test of removing node with ohe branch
     */
    @Test
    public void testRemoveLeft() {
        tree.add(5);
        tree.add(7);
        tree.add(6);
        tree.add(8);
        tree.add(4);
        tree.add(2);
        tree.add(3);
        tree.add(1);
        tree.remove(4);
        assertEquals(7, tree.size());

        Iterator<Integer> it = tree.iterator();

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(1));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(2));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(3));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(5));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(6));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(7));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(8));
        assertFalse(it.hasNext());
    }

    /**
     * Simple test of removing node with ohe branch
     */
    @Test
    public void testRemoveRight() {
        tree.add(5);
        tree.add(6);
        tree.add(8);
        tree.add(9);
        tree.add(7);
        tree.add(4);
        tree.add(2);
        tree.add(3);
        tree.add(1);
        tree.remove(6);
        assertEquals(8, tree.size());

        Iterator<Integer> it = tree.iterator();

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(1));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(2));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(3));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(4));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(5));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(7));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(8));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(9));
        assertFalse(it.hasNext());
    }

    /**
     * Simple test of removing node with two branches
     */
    @Test
    public void testRemoveHard1() {
        tree.add(8);
        tree.add(4);
        tree.add(12);
        tree.add(2);
        tree.add(6);
        tree.add(10);
        tree.add(14);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);
        tree.add(11);
        tree.add(13);
        tree.add(15);

        tree.remove(4);
        assertEquals(14, tree.size());

        Iterator<Integer> it = tree.iterator();

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(1));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(2));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(3));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(5));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(6));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(7));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(8));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(9));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(10));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(11));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(12));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(13));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(14));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(15));
        assertFalse(it.hasNext());
    }

    /**
     * Simple test of removing node with two branches
     */
    @Test
    public void testRemoveHard2() {
        tree.add(8);
        tree.add(4);
        tree.add(12);
        tree.add(2);
        tree.add(6);
        tree.add(10);
        tree.add(14);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);
        tree.add(11);
        tree.add(13);
        tree.add(15);

        tree.remove(12);
        assertEquals(14, tree.size());

        Iterator<Integer> it = tree.iterator();

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(1));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(2));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(3));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(4));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(5));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(6));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(7));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(8));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(9));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(10));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(11));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(13));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(14));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(15));
        assertFalse(it.hasNext());
    }

    /**
     * Simple test of removing many nodes
     */
    @Test
    public void testRemoveHardcoreMany() {
        tree.add(8);
        tree.add(4);
        tree.add(12);
        tree.add(2);
        tree.add(6);
        tree.add(10);
        tree.add(14);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);
        tree.add(11);
        tree.add(13);
        tree.add(15);

        tree.remove(3);
        assertEquals(14, tree.size());
        tree.remove(2);
        assertEquals(13, tree.size());
        tree.remove(4);
        assertEquals(12, tree.size());
        tree.remove(12);
        assertEquals(11, tree.size());
        tree.remove(8);
        assertEquals(10, tree.size());
        tree.remove(333);
        assertEquals(10, tree.size());

        Iterator<Integer> it = tree.iterator();

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(1));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(5));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(6));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(7));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(9));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(10));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(11));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(13));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(14));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(15));
        assertFalse(it.hasNext());
    }

    /**
     * Simple test of removing not contained element
     */
    @Test
    public void testRemoveNotExists() {
        tree.add(5);
        tree.add(8);
        tree.add(7);
        tree.add(9);
        tree.add(3);
        tree.add(4);
        tree.add(2);

        tree.remove(50);
        assertEquals(7, tree.size());

        Iterator<Integer> it = tree.iterator();

        assertTrue(it.hasNext());
        assertTrue(it.next().equals(2));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(3));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(4));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(5));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(7));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(8));
        assertTrue(it.hasNext());
        assertTrue(it.next().equals(9));
        assertFalse(it.hasNext());
    }

    /**
     * Simple test of taking floor value
     */
    @Test
    public void testFloor() {
        tree.add(8);
        tree.add(4);
        tree.add(12);
        tree.add(2);
        tree.add(6);
        tree.add(10);
        tree.add(14);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);
        tree.add(11);
        tree.add(13);
        tree.add(15);

        assertTrue(tree.floor(4).equals(4));
        assertTrue(tree.floor(100).equals(15));
        assertNull(tree.floor(-1));

        tree.remove(4);

        assertTrue(tree.floor(4).equals(3));
    }

    /**
     * Simple test of taking lower value
     */
    @Test
    public void testLower() {
        tree.add(8);
        tree.add(4);
        tree.add(12);
        tree.add(2);
        tree.add(6);
        tree.add(10);
        tree.add(14);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);
        tree.add(11);
        tree.add(13);
        tree.add(15);

        assertTrue(tree.lower(5).equals(4));
        assertTrue(tree.lower(4).equals(3));
        assertTrue(tree.lower(100).equals(15));
        assertNull(tree.lower(-1));

        tree.remove(4);

        assertTrue(tree.lower(5).equals(3));
    }

    /**
     * Simple test of taking ceiling value
     */
    @Test
    public void testCeiling() {
        tree.add(8);
        tree.add(4);
        tree.add(12);
        tree.add(2);
        tree.add(6);
        tree.add(10);
        tree.add(14);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);
        tree.add(11);
        tree.add(13);
        tree.add(15);

        assertTrue(tree.ceiling(4).equals(4));
        assertTrue(tree.ceiling(-1).equals(1));
        assertNull(tree.ceiling(100));

        tree.remove(4);

        assertTrue(tree.ceiling(4).equals(5));
    }

    /**
     * Simple test of taking higher value
     */
    @Test
    public void testHigher() {
        tree.add(8);
        tree.add(4);
        tree.add(12);
        tree.add(2);
        tree.add(6);
        tree.add(10);
        tree.add(14);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);
        tree.add(11);
        tree.add(13);
        tree.add(15);

        assertTrue(tree.higher(3).equals(4));
        assertTrue(tree.higher(4).equals(5));
        assertTrue(tree.higher(-1).equals(1));
        assertNull(tree.higher(100));

        tree.remove(4);

        assertTrue(tree.higher(3).equals(5));
    }

    /**
     * Simple test of taking higher value
     */
    @Test
    public void testHigherComparator() {
        tree = new MyTreeSetImplementation<>(Comparator.reverseOrder());

        tree.add(8);
        tree.add(4);
        tree.add(12);
        tree.add(2);
        tree.add(6);
        tree.add(10);
        tree.add(14);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);
        tree.add(11);
        tree.add(13);
        tree.add(15);

        assertTrue(tree.lower(5).equals(6));
        assertTrue(tree.lower(4).equals(5));
        assertTrue(tree.lower(-1).equals(1));
        assertNull(tree.lower(100));

        tree.remove(4);

        assertTrue(tree.lower(3).equals(5));
    }

    /**
     * Simple test of descending set tree
     */
    @Test
    public void testDescendingSet() {
        tree.add(8);
        tree.add(4);
        tree.add(12);
        tree.add(2);
        tree.add(6);
        tree.add(10);
        tree.add(14);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);
        tree.add(11);
        tree.add(13);
        tree.add(15);

        MyTreeSet<Integer> descTree = tree.descendingSet();

        Iterator<Integer> it = descTree.iterator();

        for (int i = 0; i < 15; i++) {
            assertTrue(it.hasNext());
            assertTrue(it.next().equals(15 - i));
        }
        assertFalse(it.hasNext());

        it = descTree.descendingIterator();

        for (int i = 0; i < 15; i++) {
            assertTrue(it.hasNext());
            assertTrue(it.next().equals(i + 1));
        }
        assertFalse(it.hasNext());

        assertTrue(descTree.first().equals(15));
        assertTrue(descTree.last().equals(1));

        assertTrue(descTree.higher(2).equals(1));
        assertTrue(descTree.lower(2).equals(3));
        assertTrue(descTree.ceiling(1).equals(1));
        assertTrue(descTree.floor(2).equals(2));

        assertTrue(descTree.contains(2));
    }

    /**
     * Simple test of invalidation of descending iterator
     */
    @Test(expected = IllegalStateException.class)
    public void testDescendingIteratorInvalidation() {
        tree.add(8);
        tree.add(4);
        tree.add(12);
        tree.add(2);
        tree.add(6);
        tree.add(10);
        tree.add(14);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);
        tree.add(11);
        tree.add(13);
        tree.add(15);

        Iterator<Integer> it = tree.iterator();

        for (int i = 0; i < 7; i++) {
            assertTrue(it.hasNext());
            assertTrue(it.next().equals(i + 1));
        }
        tree.remove(5);
        it.next();
    }

    /**
     * Simple test of invalidation of iterator
     */
    @Test(expected = IllegalStateException.class)
    public void testIteratorInvalidation() {
        tree.add(8);
        tree.add(4);
        tree.add(12);
        tree.add(2);
        tree.add(6);
        tree.add(10);
        tree.add(14);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);
        tree.add(11);
        tree.add(13);
        tree.add(15);

        Iterator<Integer> it = tree.iterator();

        for (int i = 0; i < 7; i++) {
            assertTrue(it.hasNext());
            assertTrue(it.next().equals(i + 1));
        }
        tree.remove(5);
        it.next();
    }

    /**
     * Simple test of iterator remove
     */
    @Test
    public void testIteratorRemoveNotInvalid() {
        tree.add(8);
        tree.add(4);
        tree.add(12);
        tree.add(2);
        tree.add(6);
        tree.add(10);
        tree.add(14);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);
        tree.add(11);
        tree.add(13);
        tree.add(15);

        Iterator<Integer> it = tree.iterator();

        for (int i = 0; i < 5; i++) {
            it.next();
        }

        it.remove();

        assertEquals(14, tree.size());

        Iterator<Integer> it2 = tree.iterator();

        assertTrue(it2.hasNext());
        assertTrue(it2.next().equals(1));
        assertTrue(it2.hasNext());
        assertTrue(it2.next().equals(2));
        assertTrue(it2.hasNext());
        assertTrue(it2.next().equals(3));
        assertTrue(it2.hasNext());
        assertTrue(it2.next().equals(4));
        assertTrue(it2.hasNext());
        assertTrue(it2.next().equals(6));
        assertTrue(it2.hasNext());
        assertTrue(it2.next().equals(7));
        assertTrue(it2.hasNext());
        assertTrue(it2.next().equals(8));
        assertTrue(it2.hasNext());
        assertTrue(it2.next().equals(9));
        assertTrue(it2.hasNext());
        assertTrue(it2.next().equals(10));
        assertTrue(it2.hasNext());
        assertTrue(it2.next().equals(11));
        assertTrue(it2.hasNext());
        assertTrue(it2.next().equals(12));
        assertTrue(it2.hasNext());
        assertTrue(it2.next().equals(13));
        assertTrue(it2.hasNext());
        assertTrue(it2.next().equals(14));
        assertTrue(it2.hasNext());
        assertTrue(it2.next().equals(15));
        assertFalse(it2.hasNext());

        it.next();
    }
}