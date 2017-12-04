package ru.spbau.kirilenko.hw6FP;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A class that contains tests for all methods of the predicate interface
 */
public class PredicateTest {

    /**
     * A test that checks correctness of the alwaysTrue predicate
     */
    @Test
    public void testPredicateAlwaysTrue() {
        Predicate<Integer> predicate = Predicate.alwaysTrue();

        assertTrue(predicate.apply(10));
        assertTrue(predicate.apply(null));
        assertTrue(predicate.apply(0));
    }

    /**
     * A test that checks correctness of the alwaysFalse predicate
     */
    @Test
    public void testPredicateAlwaysFalse() {
        Predicate<Integer> predicate = Predicate.alwaysFalse();

        assertFalse(predicate.apply(10));
        assertFalse(predicate.apply(null));
        assertFalse(predicate.apply(0));
    }

    /**
     * A test that checks correctness of the "not" predicate
     */
    @Test
    public void testPredicateNot() {
        Predicate<Integer> isEven = (x) -> (x % 2) == 0;

        assertTrue(isEven.apply(10));
        assertFalse(isEven.not().apply(10));
    }

    /**
     * A test that checks correctness of the "or" predicate
     */
    @Test
    public void testPredicateOr() {
        Predicate<Integer> isEven = (x) -> (x % 2) == 0;
        Predicate<Integer> isDivSeven = (x) -> (x % 7) == 0;

        Predicate<Integer> evenOrDivSeven = isEven.or(isDivSeven);

        assertTrue(evenOrDivSeven.apply(14));
        assertTrue(evenOrDivSeven.apply(21));
        assertTrue(evenOrDivSeven.apply(8));
        assertFalse(evenOrDivSeven.apply(5));
    }

    /**
     * A test that checks correctness of the "and" predicate
     */
    @Test
    public void testPredicateAnd() {
        Predicate<Integer> isEven = (x) -> (x % 2) == 0;
        Predicate<Integer> isDivSeven = (x) -> (x % 7) == 0;

        Predicate<Integer> evenOrDivSeven = isEven.and(isDivSeven);

        assertTrue(evenOrDivSeven.apply(14));
        assertFalse(evenOrDivSeven.apply(21));
        assertFalse(evenOrDivSeven.apply(8));
        assertFalse(evenOrDivSeven.apply(5));
    }
}