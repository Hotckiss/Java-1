package ru.spbau.kirilenko.hw3Trie;

import org.junit.Test;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class TrieTest {
    Trie tr;

    /**
     * Initialize empty trie
     */
    @Before
    public void initTrie() throws Exception {
        tr = new Trie();
    }

    /**
     * A test that check adding an existing string to trie
     */
    @Test
    public void testAddFalse() throws Exception {
        tr.add("qqf");
        tr.add("qqq");
        tr.add("qqq2");
        tr.add("qqq1");

        assertFalse(tr.add("qqf"));
    }

    /**
     * A test that check adding not existing string to trie
     */
    @Test
    public void testAddTrue() throws Exception {
        tr.add("qqf");
        tr.add("qqq");
        tr.add("qqq2");
        tr.add("qqq1");

        assertTrue(tr.add("qq1f"));
    }

    /**
     * A test that check adding null string to trie
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() throws Exception {
        tr.add("qqf");
        tr.add("qqq");
        tr.add("qqq2");
        tr.add("qqq1");

        assertFalse(tr.add(null));
    }

    /**
     * A test that check contains of non-existing string in trie
     */
    @Test
    public void testContainsFalse() throws Exception {
        tr.add("qqf");
        tr.add("qqq");
        tr.add("qqq2");
        tr.add("qqq1");

        assertFalse(tr.contains("qqq3"));
        assertFalse(tr.contains("qqq7"));
    }

    /**
     * A test that check contains of existing string in trie
     */
    @Test
    public void testContainsTrue() throws Exception {
        tr.add("qqf");
        tr.add("qqq");
        tr.add("qqq2");
        tr.add("qqq1");

        assertTrue(tr.contains("qqf"));
        assertTrue(tr.contains("qqq"));
        assertTrue(tr.contains("qqq2"));
        assertTrue(tr.contains("qqq1"));
    }

    /**
     * A test that check contains of null string in trie
     */
    @Test(expected = IllegalArgumentException.class)
    public void testContainsNull() throws Exception {
        tr.add("qqf");
        tr.add("qqq");
        tr.add("qqq2");
        tr.add("qqq1");

        assertFalse(tr.contains(null));
    }

    /**
     * A test that check remove of non-existing string from trie
     */
    @Test
    public void testRemoveFalse() throws Exception {
        tr.add("qqf");
        tr.add("qqq");
        tr.add("qqq2");
        tr.add("qqq1");

        assertFalse(tr.remove("qqq3"));
        assertFalse(tr.remove("qqq7"));
    }

    /**
     * A test that check remove of existing string from trie
     */
    @Test
    public void testRemoveTrue() throws Exception {
        tr.add("qqf");
        tr.add("qqq");
        tr.add("qqq2");
        tr.add("qqq1");

        assertTrue(tr.remove("qqf"));
        assertTrue(tr.remove("qqq"));
        assertTrue(tr.remove("qqq2"));
        assertTrue(tr.remove("qqq1"));
    }

    /**
     * A test that check remove of null string from trie
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNull() throws Exception {
        tr.add("qqf");
        tr.add("qqq");
        tr.add("qqq2");
        tr.add("qqq1");

        assertFalse(tr.remove(null));
    }

    /**
     * A test that check size of empty trie
     */
    @Test
    public void testSizeEmpty() throws Exception {
        assertEquals(0, tr.size());
    }

    /**
     * A test that check size of non-empty trie
     */
    @Test
    public void testSizeNonEmpty() throws Exception {
        tr.add("qqf");
        tr.add("qqq");
        tr.add("qqq2");
        tr.add("qqq1");
        tr.add("aqqf");
        tr.add("qdfqq");
        tr.add("aaqqq2");
        tr.add("rrqqq1");
        tr.add("aaqqq2");
        tr.add("rrqqq1");

        assertEquals(8, tr.size());
    }

    /**
     * A test that check counting strings with input prefix in trie
     */
    @Test
    public void testHowManyStartsWithPrefix() throws Exception {
        tr.add("qqf");
        tr.add("qqq");
        tr.add("qqq2");
        tr.add("qqq1");
        tr.add("aqqf");
        tr.add("qdfqq");
        tr.add("aaqqq2");
        tr.add("rrqqq1");
        tr.add("aaqqq2");
        tr.add("rrqqq1");

        assertEquals(5, tr.howManyStartsWithPrefix("q"));
        assertEquals(4, tr.howManyStartsWithPrefix("qq"));
        assertEquals(2, tr.howManyStartsWithPrefix("a"));
        assertEquals(8, tr.howManyStartsWithPrefix(""));
        assertEquals(0, tr.howManyStartsWithPrefix("ttttt"));
    }

    /**
     * A test that check serialization and deserialization of empty trie
     */
    @Test
    public void testSerializeDeserializeEmptyTrie() {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
            tr.serialize(out);
            try (ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray())) {
                tr.deserialize(in);
            } catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * A test that check serialization and deserialization of non-empty trie
     */
    @Test
    public void testSerializeDeserializeNonEmptyTrie() {
        Trie newTrie = new Trie();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
            tr.add("qqf");
            tr.add("qqq");
            tr.add("qqq2");
            tr.add("qqq1");
            tr.add("aqqf");
            tr.add("qdfqq");
            tr.add("aaqqq2");
            tr.add("rrqqq1");

            tr.serialize(out);
            try (ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray())) {
                newTrie.deserialize(in);
            } catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        assertEquals(8, tr.size());
        assertTrue(tr.contains("qqf"));
        assertTrue(tr.contains("qqq"));
        assertTrue(tr.contains("qqq2"));
        assertTrue(tr.contains("qqq1"));
        assertFalse(tr.contains("qfdefqq"));
        assertFalse(tr.contains("qqqqqqqq"));
        assertFalse(tr.contains(""));

    }
}