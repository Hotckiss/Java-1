package ru.spbau.kirilenko.hw2MatrixTest;

import ru.spbau.kirilenko.hw2Matrix.Matrix;
import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEven() throws Exception {
        Matrix<Integer> matrix = new Matrix<>(new Integer[][] {{0, 0}, {0, 0}});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNotSquare() throws Exception {
        Matrix<Integer> matrix = new Matrix<>(new Integer[][] {{0, 0, 0}, {0, 0}, {0, 1, 1}});
    }

    /**
     * A tiny test to check correctness on this matrix
     */
    @Test
    public void testGetSpiralIntegersTiny() throws Exception {
        Matrix<Integer> matrix = new Matrix<>(new Integer[][] { {7} });
        Integer[] expectedAnswer = new Integer[] {7};

        assertArrayEquals(expectedAnswer, matrix.getSpiral());
    }

    /**
     * A small test to check correctness of the spiral walking
     */
    @Test
    public void testGetSpiralIntegersSmall() throws Exception {
        /*
           It's a columns! Real matrix is:
           4 2 6
           5 0 1
           8 9 1
         */
        Matrix<Integer> matrix = new Matrix<>(new Integer[][] {{4, 5, 8},
                {2, 0, 9},
                {6, 1, 1}});
        Integer[] expectedAnswer = new Integer[] {0, 2, 4, 5, 8, 9, 1, 1, 6};

        assertArrayEquals(expectedAnswer, matrix.getSpiral());
    }

    /**
     * A medium-sized test to check correctness of the spiral walking with Integers
     */
    @Test
    public void testGetSpiralIntegersMedium() throws Exception {
        Matrix<Integer> matrix = new Matrix<>(new Integer[][] {{4, 5, 8, 5, 6},
                {2, 0, 9, 6, 7},
                {2, 0, 4, 1, 2},
                {2, 5, 0, 33, 1},
                {6, 1, 1, 77, 5}});
        Integer[] expectedAnswer = new Integer[] {4, 0, 0, 9, 6, 1, 33, 0, 5, 2, 2, 2, 4, 5, 8, 5, 6, 7, 2, 1, 5, 77, 1, 1, 6};

        assertArrayEquals(expectedAnswer, matrix.getSpiral());
    }

    /**
     * A big test to check correctness of the spiral walking with Integers
     */
    @Test
    public void testGetSpiralIntegersBig() throws Exception {
        Matrix<Integer> matrix = new Matrix<>(new Integer[][] {{4, 5, 8, 5, 6, 6, 3},
                {2, 0, 9, 6, 7, 8, 9},
                {5, 0, 4, 1, 2, 3, 4},
                {24, 8, 4, 11, 2, 8, 2},
                {2, 0, 4, 1, 2, 4, 5},
                {33, 5, 0, 33, 1, 6, 7},
                {6, 1, 1, 77, 5, 8, 9}});
        Integer[] expectedAnswer = new Integer[] {11, 4, 4, 1, 2, 2, 2, 1, 4, 0, 8, 0, 0, 9, 6, 7, 8, 3, 8, 4, 6, 1, 33, 0, 5, 33, 2, 24, 5, 2, 4, 5, 8, 5, 6, 6, 3, 9, 4, 2, 5, 7, 9, 8, 5, 77, 1, 1, 6};

        assertArrayEquals(expectedAnswer, matrix.getSpiral());
    }

    /**
     * A small test to check correctness of the spiral walking with Strings
     */
    @Test
    public void testGetSpiralString() throws Exception {
        Matrix<String> matrix = new Matrix<>(new String[][] {{"three", "four", "five"}, {"two", "one", "six"}, {"nine", "eight", "seven"}});
        String[] expectedAnswer = new String[] {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        assertArrayEquals(expectedAnswer, matrix.getSpiral());
    }

    /**
     * A test to check correctness of the sorting columns of Integers
     */
    @Test
    public void testSortIntegers() throws Exception {
        /*
           It's a columns! Real matrix is:
           4 2 6
           5 0 1
           8 9 1
           Sorted should be:
           2 4 6
           0 5 1
           9 8 1
           And input-like style:
           {{2, 0, 9},
            {4, 5, 8},
            {6, 1, 1}}
         */
        Matrix<Integer> matrix = new Matrix<>(new Integer[][] {{4, 5, 8},
                {2, 0, 9},
                {6, 1, 1}});
        matrix.sort();

        Integer[] expectedAnswer = new Integer[] {5, 4, 2, 0, 9, 8, 1, 1, 6};

        assertArrayEquals(expectedAnswer, matrix.getSpiral());
    }

    /**
     * A test to check correctness of the sorting columns of Strings
     */
    @Test
    public void testSortStrings() throws Exception {
        Matrix<String> matrix = new Matrix<>(new String[][] {{"three", "four", "five"}, {"two", "one", "six"}, {"nine", "eight", "seven"}});
        String[] expectedAnswer = new String[] {"four", "three", "nine", "eight", "seven", "five", "six", "one", "two"};

        matrix.sort();

        assertArrayEquals(expectedAnswer, matrix.getSpiral());
    }
}