package ru.spbau.kirilenko.hw2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KeyValuePairTest {
    private KeyValuePair kvp;

    /**
     * Tests default constructor of the pair
     * @throws Exception
     */
    @Test
    public void testConstructor1() throws Exception {
        kvp = new KeyValuePair();
    }

    /**
     * Tests constructor of the pair from two strings
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2() throws Exception {
        kvp = new KeyValuePair(null, "test");
    }

    /**
     * Tests get key from the pair
     * @throws Exception
     */
    @Test
    public void testGetKey() throws Exception {
        kvp = new KeyValuePair("a", "b");
        assertEquals(kvp.getKey(), "a");
    }

    /**
     * Tests get value from the pair
     * @throws Exception
     */
    @Test
    public void getValue() throws Exception {
        kvp = new KeyValuePair("a", "b");
        assertEquals(kvp.getValue(), "b");
    }

    /**
     * Tests set new value of the pair
     * @throws Exception
     */
    @Test
    public void setNewValue() throws Exception {
        kvp = new KeyValuePair("a", "b");
        kvp.setNewValue("qqf");
        assertEquals(kvp.getValue(), "qqf");
    }
}