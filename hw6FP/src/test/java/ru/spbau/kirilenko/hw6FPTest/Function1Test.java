package ru.spbau.kirilenko.hw6FPTest;

import org.junit.Test;
import ru.spbau.kirilenko.hw6FP.Function1;

import static org.junit.Assert.*;

/**
 * A class that contains tests for all methods of the single argument function interface
 */
public class Function1Test {
    /**
     * A test that checks correctness of the apply for single argument function
     */
    @Test
    public void testFunction1apply() {
        Integer number = 3;

        Function1<Integer, Integer> func = (x) -> x * x;

        assertEquals(0, func.apply(number).compareTo(9));
    }

    /**
     * A test that checks correctness of the composition for single argument function
     */
    @Test
    public void testFunction1Compose1() {
        Integer number = 3;

        Function1<Integer, Integer> funcF = (x) -> x * x;
        Function1<Integer, Integer> funcG = (x) -> 7 * x;
        Function1<Integer, Integer> funcGF = funcF.compose(funcG);
        Function1<Integer, Integer> funcFG =  funcG.compose(funcF);

        assertEquals(0, funcGF.apply(number).compareTo(63));
        assertEquals(0, funcFG.apply(number).compareTo(441));
    }

    /**
     * A test that checks correctness of the composition for single argument function
     */
    @Test
    public void testFunction1Compose2() {
        Integer number = 30;

        Function1<Integer, Integer> funcF = (x) -> x * x;
        Function1<Number, String> funcG = (x) -> x.toString();
        Function1<Integer, String> funcGF = funcF.compose(funcG);

        assertTrue(funcGF.apply(number).equals("900"));
    }
}