package ru.spbau.kirilenko.hw6FP;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * A class that contains tests for all methods of the collections class
 */
public class CollectionsTest {

    /**
     * A test that checks correctness of the map function with sqr function
     */
    @Test
    public void testCollectionsMap1() {
        ArrayList<Number> list = new ArrayList<>();
        Function1<Number, Number> function = (x) -> 2 * x.doubleValue();

        list.add(4);
        list.add(3.95);
        list.add(0);
        list.add(7.7);
        list.add(-5);

        List<Number> actualList = Collections.map(function, list);

        assertEquals(8.0, actualList.get(0).doubleValue(), 0);
        assertEquals(7.9, actualList.get(1).doubleValue(), 0);
        assertEquals(0.0, actualList.get(2).doubleValue(), 0);
        assertEquals(15.4, actualList.get(3).doubleValue(), 0);
        assertEquals(-10.0, actualList.get(4).doubleValue(), 0);
    }

    /**
     * A test that checks correctness of the map function with sqrt function
     */
    @Test
    public void testCollectionsMap2() {
        ArrayList<Number> list = new ArrayList<>();
        Function1<Number, Number> function = (x) -> Math.sqrt(x.doubleValue());

        list.add(4);
        list.add(3.95);
        list.add(0);
        list.add(7.7);
        list.add(5);

        List<Number> actualList = Collections.map(function, list);

        assertEquals(2.0, actualList.get(0).doubleValue(), 0);
        assertEquals(1.99, actualList.get(1).doubleValue(), 0.005);
        assertEquals(0.0, actualList.get(2).doubleValue(), 0);
        assertEquals(2.77, actualList.get(3).doubleValue(), 0.005);
        assertEquals(2.24, actualList.get(4).doubleValue(), 0.005);
    }

    /**
     * A test that checks correctness of the filter function
     */
    @Test
    public void testCollectionsFilter() {
        ArrayList<Number> list = new ArrayList<>();
        Predicate<Number> predicate = (x) -> x.doubleValue() == Math.abs(x.doubleValue());
        Function1<Number, Number> function = (x) -> Math.sqrt(x.doubleValue());

        list.add(4);
        list.add(3.95);
        list.add(0);
        list.add(7.7);
        list.add(-5);
        list.add(-7);

        List<Number> actualList = Collections.filter(predicate, list);

        assertEquals(4, actualList.size());

        actualList = Collections.map(function, list);

        assertEquals(2.0, actualList.get(0).doubleValue(), 0);
        assertEquals(1.99, actualList.get(1).doubleValue(), 0.005);
        assertEquals(0.0, actualList.get(2).doubleValue(), 0);
        assertEquals(2.77, actualList.get(3).doubleValue(), 0.005);
    }

    /**
     * A test that checks correctness of the takeWhile function
     */
    @Test
    public void testCollectionsTakeWhile() {
        ArrayList<Number> list = new ArrayList<>();
        Predicate<Number> predicate = (x) -> x.doubleValue() == Math.abs(x.doubleValue());

        list.add(4);
        list.add(3.95);
        list.add(0);
        list.add(-1);
        list.add(7.7);
        list.add(-5);
        list.add(-7);

        List<Number> actualList = Collections.takeWhile(predicate, list);

        assertEquals(3, actualList.size());

        assertEquals(4.0, actualList.get(0).doubleValue(), 0);
        assertEquals(3.95, actualList.get(1).doubleValue(), 0);
        assertEquals(0.0, actualList.get(2).doubleValue(), 0);
    }

    /**
     * A test that checks correctness of the takeUnless function
     */
    @Test
    public void testCollectionsTakeUnless() {
        ArrayList<Number> list = new ArrayList<>();
        Predicate<Number> predicate = (x) -> x.doubleValue() == Math.abs(x.doubleValue());

        list.add(-4);
        list.add(-3.95);
        list.add(0);
        list.add(-1);
        list.add(7.7);
        list.add(-5);
        list.add(-7);

        List<Number> actualList = Collections.takeUnless(predicate, list);

        assertEquals(2, actualList.size());

        assertEquals(-4.0, actualList.get(0).doubleValue(), 0);
        assertEquals(-3.95, actualList.get(1).doubleValue(), 0);
    }

    /**
     * A test that checks correctness of the foldl function
     */
    @Test
    public void testCollectionsFoldl() {
        ArrayList<Number> list = new ArrayList<>();
        Function2<Double, Number, Double> function = (x, y) -> x + y.doubleValue();

        list.add(-4);
        list.add(-3.95);
        list.add(0);
        list.add(-1);
        list.add(7.7);
        list.add(-5);
        list.add(-7);

        Double actualSum = Collections.foldl(function, 0.0, list);

        assertEquals(0, actualSum.compareTo(-13.25));
    }

    /**
     * A test that checks correctness of the foldr function
     */
    @Test
    public void testCollectionsFoldr() {
        ArrayList<String> list = new ArrayList<>();
        Function2<String, String, String> function = (x, y) -> y.concat(x);

        list.add("4");
        list.add("3.95");
        list.add("abc");
        list.add("-1");

        String actualString = Collections.foldr(function, "", list);

        assertEquals("-1abc3.954", actualString);
    }
}