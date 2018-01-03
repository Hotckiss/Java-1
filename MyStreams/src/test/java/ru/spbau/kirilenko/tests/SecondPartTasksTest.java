package ru.spbau.kirilenko.tests;

import org.junit.Test;
import ru.spbau.kirilenko.SecondPartTasks;

import java.util.*;

import static org.junit.Assert.*;
import static ru.spbau.kirilenko.SecondPartTasks.*;

/**
 * A class that tests all methods of SecondPartTasks
 */
public class SecondPartTasksTest {
    private static final String dir = new String(System.getProperty("user.dir") + "/src/test/resources/");

    /**
     * Method that checks that FindQuotes finds only
     * correct strings from the input files
     */
    @Test
    public void testFindQuotes() {
        ArrayList<String> files = new ArrayList<>();
        files.add(dir + "1.txt");
        files.add(dir + "2.txt");

        ArrayList<String> actual = (ArrayList<String>)SecondPartTasks.findQuotes(files, "abacaba");

        assertEquals(5, actual.size());
        assertEquals("s lkzbslzdl    opsifv abacabasdfovjarobvearblksdfvl;", actual.get(0));
        assertEquals("abacaba", actual.get(1));
        assertEquals("abacabadabacaba", actual.get(2));
        assertEquals("abacaba", actual.get(3));
        assertEquals("                       ########################## abacaba", actual.get(4));
    }

    /**
     * A test that checks that probability to shoot in a circle is correct
     */
    @Test
    public void testPiDividedBy4() {
        assertEquals(Math.PI / 4, piDividedBy4(), 0.01);
    }

    /**
     * Test that checks correctness of finding an author
     * with the longest length of books in common
     */
    @Test
    public void testFindPrinter() {
        HashMap<String, List<String>> writers = new HashMap<>();

        writers.put("Vasya", Arrays.asList("sdvkasdvlabv", "sdvkaklvqwreldklvlfdblertlklkertb", "aweldsojegkjlNVrlg;elrhuWALK;VHERKHGHKLJRGHA,EJGHAKSDV") );
        writers.put("Petya", Arrays.asList("sdvkabv", "sdvkaklvqertlklkertb", "aweldsojegkjlNVrlg;eLJRGHA,EJGHAKSDV") );
        writers.put("Andrey", Arrays.asList("sdvkabvhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhaweldsojegkjlNVrlg;eLJRGHA,EJGHAKSDV") );

        assertEquals("Andrey", SecondPartTasks.findPrinter(writers));
    }

    /**
     * Test that checks correctness of finding an author
     * with the longest length of books in common with empty map
     */
    @Test
    public void testFindPrinterNull() {
        HashMap<String, List<String>> writers = new HashMap<>();

        assertNull(SecondPartTasks.findPrinter(writers));
    }

    /**
     * Test that checks correctness of calculating
     * total count of each good from companies
     */
    @Test
    public void testCalculateGlobalOrder() {
        List<Map<String, Integer>> goods = new ArrayList<>();

        Map<String, Integer> company1 = new HashMap<>();
        company1.put("water", 100);
        company1.put("doshirak", 33);
        company1.put("Lays", 12);

        Map<String, Integer> company2 = new HashMap<>();
        company2.put("water", 210);
        company2.put("milk", 303);
        company2.put("snickers", 112);

        Map<String, Integer> company3 = new HashMap<>();
        company3.put("Lays", 210);
        company3.put("snickers", 303);

        goods.add(company1);
        goods.add(company2);
        goods.add(company3);

        HashMap<String, Integer> actual = (HashMap<String, Integer>)SecondPartTasks.calculateGlobalOrder(goods);

        assertEquals(5, actual.size());

        assertEquals((Integer) 310, actual.get("water"));
        assertEquals((Integer) 33, actual.get("doshirak"));
        assertEquals((Integer) 415, actual.get("snickers"));
        assertEquals((Integer) 222, actual.get("Lays"));
        assertEquals((Integer) 303, actual.get("milk"));
    }
}