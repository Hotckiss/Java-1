package ru.spbau.kirilenko.hwTests;

import org.junit.Before;
import org.junit.Test;
import ru.spbau.kirilenko.hw2.HashTable;
import static org.junit.Assert.*;

public class HashTableTest {

    private HashTable hashTable;

    /**
     * Create HashTable for tests
     */
    @Before
    public void createHashTableForTesting() throws Exception {
        hashTable = new HashTable(30);
    }

    /**
     * Test correctness of constructor
     */
    @Test
    public void testConstructor() throws Exception {
        hashTable = new HashTable(17);
    }

    /**
     * Tests size of hashTable if empty
     */
    @Test
    public void sizeEmpty() throws Exception {
        hashTable = new HashTable(30);
        assertEquals(0, hashTable.size());

    }

    /**
     * Tests size of hashTable if not empty
     */
    @Test
    public void sizeNotEmpty() throws Exception {
        hashTable = new HashTable(30);

        hashTable.put("key", "null");
        hashTable.put("key", "null");

        assertEquals(1, hashTable.size());

    }

    /**
     * Tests put to hashTable if key is wrong
     */
    @Test(expected = IllegalArgumentException.class)
    public void putNull() throws Exception {
        hashTable.put(null, "null");
    }

    /**
     * Tests put to hashTable if key is correct
     */
    @Test
    public void putNotNull() throws Exception {
        hashTable.put("key", "null");

        assertEquals("null", hashTable.get("key"));
        assertEquals(1, hashTable.size());
    }

    /**
     * Tests put to hashTable if some keys is same
     */
    @Test
    public void putWithEqualKeys() throws Exception {
        hashTable.put("key", "null");
        hashTable.put("key", "null");
        hashTable.put("key", "null2");
        hashTable.put("key", "null1");

        assertEquals("null1", hashTable.get("key"));
        assertEquals(1, hashTable.size());
    }

    /**
     * Tests put to hashTable if all keys are not same
     */
    @Test
    public void putNotSameKeys() throws Exception {
        hashTable.put("key", "null");
        hashTable.put("key1", "null");
        hashTable.put("key2", "null");

        assertEquals("null", hashTable.get("key1"));
        assertEquals("null", hashTable.get("key"));
        assertEquals("null", hashTable.get("key2"));
        assertEquals(3, hashTable.size());
    }

    /**
     * Tests size of hashTable if it is not empty
     */
    @Test
    public void sizeNotZero() throws Exception {
        hashTable.put("key", "null");
        hashTable.put("key", "null");
        hashTable.put("key2", "null2");
        hashTable.put("key1", "null1");

        assertEquals(3, hashTable.size());
    }

    /**
     * Tests size of hashTable after clear()
     */
    @Test
    public void testClear() throws Exception {
        hashTable.put("key", "null");
        hashTable.put("key", "null");
        hashTable.put("key2", "null2");
        hashTable.put("key1", "null1");
        hashTable.clear();

        assertEquals(0, hashTable.size());

        hashTable.put("key", "null");

        assertEquals(1, hashTable.size());
        assertEquals("null", hashTable.get("key"));
    }

    /**
     * Tests get from hashTable if element exists
     */
    @Test
    public void testGetNotNull() throws Exception {
        hashTable.put("key", "null");
        hashTable.put("key1", "null");
        hashTable.put("key2", "null");

        assertEquals("null", hashTable.get("key"));
        assertEquals("null", hashTable.get("key1"));
        assertEquals("null", hashTable.get("key2"));
    }

    /**
     * Tests get from hashTable if element does not exists
     */
    @Test
    public void testGetNull() throws Exception {
        hashTable.put("key", "null");

        assertEquals(null, hashTable.get("key1"));
    }

    /**
     * Tests contains in hashTable if element exists
     */
    @Test
    public void containsTrue() throws Exception {
        hashTable.put("key", "null");

        assertEquals(true, hashTable.contains("key"));
    }

    /**
     * Tests contains in hashTable if element is not exists
     */
    @Test
    public void containsFalse() throws Exception {
        hashTable.put("key", "null");

        assertEquals(false, hashTable.contains("key1"));
    }

    /**
     * Tests remove from hashTable if key is wrong
     */
    @Test(expected = IllegalArgumentException.class)
    public void removeException() throws Exception {
        hashTable.put("key", "null");
        hashTable.remove(null);
    }

    /**
     * Tests remove from hashTable if key is not in HashTable
     */
    @Test
    public void removeNull() throws Exception {
        hashTable.put("key", "null");
        hashTable.put("key1", "null");
        hashTable.put("key2", "null");

        assertEquals(3, hashTable.size());
        assertEquals(null, hashTable.remove("key4"));
        assertEquals(3, hashTable.size());
    }

    /**
     * Tests remove from hashTable if key is in HashTable
     */
    @Test
    public void removeNotNull() throws Exception {
        hashTable.put("key", "null");
        hashTable.put("key1", "null1");
        hashTable.put("key2", "null2");

        assertEquals(3, hashTable.size());
        assertEquals("null2", hashTable.remove("key2"));
        assertEquals(2, hashTable.size());
    }

    /**
     * Tests resize of hashTable to save complexity
     */
    @Test
    public void reinitializationCalling() throws Exception {
        for (int i = 0; i < 100; ++i) {
            hashTable.put("key" + i, "null" + i);
        }


        assertEquals("null2", hashTable.remove("key2"));

    }
}