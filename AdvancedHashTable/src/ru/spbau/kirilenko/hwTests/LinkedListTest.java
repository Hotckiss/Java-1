package ru.spbau.kirilenko.hwTests;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.kirilenko.hw2.LinkedList;

import static org.junit.Assert.*;

public class LinkedListTest {
    private LinkedList ll;

    /**
     * Create LinkedList for tests
     */
    @Before
    public void createLinkedListForTesting() throws Exception {
        ll = new LinkedList();
    }

    /**
     * Test correctness of constructor
     */
    @Test
    public void testConstructor() throws Exception {
        ll = new LinkedList();
    }

    /**
     * Tests size of list if it is empty
     */
    @Test
    public void sizeZero() throws Exception {
        assertEquals(0, ll.size());
    }

    /**
     * Tests put to the list if key is wrong
     */
    @Test(expected = IllegalArgumentException.class)
    public void putNull() throws Exception {
        ll.put(null, "null");
    }

    /**
     * Tests put to the list if key is correct
     */
    @Test
    public void putNotNull() throws Exception {
        ll.put("dsv", "null");

        assertEquals("null", ll.get("dsv"));
        assertEquals(1, ll.size());
    }

    /**
     * Tests put to the list if some keys is same
     */
    @Test
    public void putWithEqualKeys() throws Exception {
        ll.put("dsv", "null");
        ll.put("dsv", "null");
        ll.put("dsv", "null2");
        ll.put("dsv", "null1");

        assertEquals("null1", ll.get("dsv"));
        assertEquals(1, ll.size());
        assertEquals("null1", ll.put("dsv", "null2"));
    }

    /**
     * Tests put to the list if all keys are not same
     */
    @Test
    public void putNotSameKeys() throws Exception {
        ll.put("dsv", "null");
        ll.put("dsv1", "null");
        ll.put("dsv2", "null");

        assertEquals("null", ll.get("dsv1"));
        assertEquals("null", ll.get("dsv"));
        assertEquals("null", ll.get("dsv2"));
        assertEquals(3, ll.size());
    }

    /**
     * Tests size of list if it is not empty
     */
    @Test
    public void sizeNotZero() throws Exception {
        ll.put("dsv", "null");
        ll.put("dsv", "null");
        ll.put("dsv2", "null2");
        ll.put("dsv1", "null1");

        assertEquals(3, ll.size());
    }

    /**
     * Tests size of list if it was cleared
     */
    @Test
    public void testClear() throws Exception {
        ll.put("dsv", "null");
        ll.put("dsv", "null");
        ll.put("dsv2", "null2");
        ll.put("dsv1", "null1");
        ll.clear();

        assertEquals(0, ll.size());

        ll.put("dsv", "null");

        assertEquals(1, ll.size());
        assertEquals("null", ll.get("dsv"));
    }

    /**
     * Tests get from list if key existed
     */
    @Test
    public void testGetNotNull() throws Exception {
        ll.put("dsv", "null");
        ll.put("dsv1", "null");
        ll.put("dsv2", "null");

        assertEquals("null", ll.get("dsv"));
        assertEquals("null", ll.get("dsv1"));
        assertEquals("null", ll.get("dsv2"));
    }

    /**
     * Tests get from list if key not existed
     */
    @Test
    public void testGetNull() throws Exception {
        ll.put("dsv", "null");

        assertEquals(null, ll.get("dsv1"));
    }

    /**
     * Tests contains in list if key existed
     */
    @Test
    public void containsTrue() throws Exception {
        ll.put("dsv", "null");

        assertEquals(true, ll.contains("dsv"));
    }

    /**
     * Tests contains in list if key not existed
     */
    @Test
    public void containsFalse() throws Exception {
        ll.put("dsv", "null");

        assertEquals(false, ll.contains("dsv1"));
    }

    /**
     * Tests remove from list if key is wrong
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeException() throws Exception {
        ll.put("dsv", "null");
        ll.remove(null);
    }

    /**
     * Tests remove from list if key was not in list
     */
    @Test
    public void removeNull() throws Exception {
        ll.put("dsv", "null");
        ll.put("dsv1", "null");
        ll.put("dsv2", "null");

        assertEquals(3, ll.size());
        assertEquals(null, ll.remove("dsv4"));
        assertEquals(3, ll.size());
    }

    /**
     * Tests remove from list if key was in list
     */
    @Test
    public void removeNotNull() throws Exception {
        ll.put("dsv", "null");
        ll.put("dsv1", "null1");
        ll.put("dsv2", "null2");

        assertEquals(3, ll.size());
        assertEquals("null2", ll.remove("dsv2"));
        assertEquals(2, ll.size());
    }

    /**
     * Tests pop from list if it is empty
     */
    @Test
    public void popFrontNull() throws Exception {
        assertEquals(0, ll.size());
        assertEquals(null, ll.popFront());
        assertEquals(0, ll.size());
    }

    /**
     * Tests pop from list if it is not empty
     */
    @Test
    public void popFrontNotNull() throws Exception {
        ll.put("dsv", "null");
        ll.put("dsv1", "null1");
        ll.put("dsv2", "null2");
        assertEquals(3, ll.size());
        assertEquals("null", ll.popFront().getValue());
        assertEquals(2, ll.size());
    }

}