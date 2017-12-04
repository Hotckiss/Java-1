package ru.spbau.kirilenko.hw6FPTest;

import org.junit.Test;
import ru.spbau.kirilenko.hw6FP.Function1;
import ru.spbau.kirilenko.hw6FP.Function2;

import static org.junit.Assert.*;

/**
 * A class that contains tests for all methods of the two argument function interface
 */
public class Function2Test {
    /**
     * A test that checks correctness of the apply for two argument function
     */
    @Test
    public void testFunction2apply() {
        Integer numberX = 3;
        Integer numberY = 7;

        Function2<Integer, Integer, Integer> func = (x, y) -> x * y + x - y;

        assertEquals(0, func.apply(numberX, numberY).compareTo(17));
    }

    /**
     * A test that checks correctness of the composition for two argument function
     */
    @Test
    public void testFunction2Compose() {
        Integer numberX = 3;
        Integer numberY = 7;

        Function2<Integer, Integer, Integer> funcF = (x, y) -> x * y + x - y;
        Function1<Integer, Integer> funcG = (x) -> 7 * x;
        Function2<Integer, Integer, Integer> funcGF = funcF.compose(funcG);

        assertEquals(0, funcGF.apply(numberX, numberY).compareTo(119));
    }

    /**
     * A test that checks correctness of the bind first argument for two argument function
     */
    @Test
    public void testFunction1Bind1() {
        Integer numberY = 7;

        Function2<Integer, Integer, Integer> funcF = (x, y) -> x * y + x - y;
        Function1<Integer, Integer> funcG = funcF.bind1(10);

        assertEquals(0, funcG.apply(numberY).compareTo(73));
    }

    /**
     * A test that checks correctness of the bind second argument for two argument function
     */
    @Test
    public void testFunction1Bind2() {
        Integer numberX = 7;

        Function2<Integer, Integer, Integer> funcF = (x, y) -> x * y + x - y;
        Function1<Integer, Integer> funcG = funcF.bind2(17);

        assertEquals(0, funcG.apply(numberX).compareTo(109));
    }

    /**
     * A test that checks correctness of the carrying for two argument function
     */
    @Test
    public void testFunction1Curry() {
        Integer number = 4;

        Function2<Integer, Integer, Integer> funcF = (x, y) -> x * y + x - y;
        Function1<Integer, Function1<Integer, Integer>> carried = funcF.curry();
        Function1<Integer, Integer> funcG = carried.apply(7);

        assertEquals(0, funcG.apply(number).compareTo(25));
    }
}