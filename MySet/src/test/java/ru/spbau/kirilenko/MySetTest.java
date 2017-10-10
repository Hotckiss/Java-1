package ru.spbau.kirilenko;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A class that contains tests to all public methods of MySet class
 */
public class MySetTest {
    /**
     * Test that checks correctness of the adding null element
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() throws Exception {
        MySet<Integer> mySet = new MySet<>();
        mySet.add(null);
    }

    /**
     * Test that checks correctness of the adding elements of Integer
     */
    @Test
    public void testAddIntegers() throws Exception {
        MySet<Integer> mySet = new MySet<>();
        for (int i = -30; i <= 30; i++) {
            assertTrue(mySet.add(i));
            assertFalse(mySet.add(i));
        }
    }

    /**
     * Test that checks correctness of the adding elements of String
     */
    @Test
    public void testAddStrings() throws Exception {
        MySet<String> mySet = new MySet<>();
        char ch = 'a';
        String str = "a";
        for (int i = 1; i <= 10; i++) {
            ch++;
            assertTrue(mySet.add(str));
            assertFalse(mySet.add(str));
            str = str + ch + (new StringBuilder(str).reverse().toString());
        }
    }

    /**
     * Test that checks correctness of containing null element
     */
    @Test(expected = IllegalArgumentException.class)
    public void testContainsNull() throws Exception {
        MySet<Integer> mySet = new MySet<>();
        mySet.contains(null);
    }

    /**
     * Test that checks correctness of the containing elements of Integer
     */
    @Test
    public void testContainsIntegers() throws Exception {
        MySet<Integer> mySet = new MySet<>();
        for (int i = -30; i <= 30; i+=2) {
            assertTrue(mySet.add(i));
            assertTrue(mySet.contains(i));
            assertFalse(mySet.contains(i + 1));
        }
    }

    /**
     * Test that checks correctness of containing elements of String
     */
    @Test
    public void testContainsStrings() throws Exception {
        MySet<String> mySet = new MySet<>();
        char ch = 'a';
        String str = "a";
        for (int i = 1; i <= 10; i++) {
            ch++;
            assertTrue(mySet.add(str));
            assertTrue(mySet.contains(str));
            assertFalse(mySet.contains(str + "that string is not contained in set"));
            str = str + ch + (new StringBuilder(str).reverse().toString());
        }
    }

    /**
     * Checks the size of empty Set
     */
    @Test
    public void sizeEmpty() throws Exception {
        MySet<Integer> mySet = new MySet<>();
        assertEquals(0, mySet.size());
    }

    /**
     * Checks the size of non-empty Set
     */
    @Test
    public void sizeNonEmpty() throws Exception {
        MySet<Integer> mySet = new MySet<>();
        for (int i = -30; i <= 30; i++) {
            mySet.add(i);
        }
        assertEquals(61, mySet.size());
    }
}
