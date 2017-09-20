package ru.spbau.kirilenko.hwTests;

import org.junit.Test;
import ru.spbau.kirilenko.hw2.KeyValuePair;
import static org.junit.Assert.assertEquals;

public class KeyValuePairTest {
    private KeyValuePair kvp;

    /**
     * Tests default constructor of the pair
     */
    @Test
    public void testConstructor1() throws Exception {
        kvp = new KeyValuePair();
    }

    /**
     * Tests constructor of the pair from two strings
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2() throws Exception {
        kvp = new KeyValuePair(null, "test");
    }

    /**
     * Tests get key from the pair
     */
    @Test
    public void testGetKey() throws Exception {
        kvp = new KeyValuePair("a", "b");
        assertEquals(kvp.getKey(), "a");
    }

    /**
     * Tests get value from the pair
     */
    @Test
    public void getValue() throws Exception {
        kvp = new KeyValuePair("a", "b");
        assertEquals(kvp.getValue(), "b");
    }

    /**
     * Tests set new value of the pair
     */
    @Test
    public void setNewValue() throws Exception {
        kvp = new KeyValuePair("a", "b");
        kvp.setNewValue("qqf");
        assertEquals(kvp.getValue(), "qqf");
    }
}