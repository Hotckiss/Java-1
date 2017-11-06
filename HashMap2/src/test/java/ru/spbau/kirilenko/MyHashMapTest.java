package ru.spbau.kirilenko;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class MyHashMapTest {

    /**
     * Correctness of the get and put
     */
    @Test
    public void test1() {
        MyHashMap m = new MyHashMap<Integer, Integer>(1007);
        m.put(7, 9);
        m.put(8, 2);
        m.put(9, 4);
        m.put(1, 5);
        m.remove(1);
        assertEquals(9, m.get(7));
    }

    /**
     * Correctness of the get and put
     */
    @Test
    public void test2() {
        MyHashMap m = new MyHashMap<Integer, String>(1007);
        m.put(7, "str1");
        m.put(8, "str3");
        m.put(9, "str5");
        m.put(1, "str8");
        m.remove(8);
        assertEquals("str5", m.get(9));
    }

    /**
     * Corectness of the order of output
     */
    @Test
    public void test3() {
        MyHashMap m = new MyHashMap<Integer, String>(1007);
        m.put(7, "str1");
        m.put(8, "str3");
        m.put(9, "str5");
        m.put(1, "str8");
        m.remove(8);
        System.out.println(m.values());
    }
}