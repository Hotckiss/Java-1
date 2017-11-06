package ru.spbau.kirilenko.hw2MatrixTest;

import ru.spbau.kirilenko.hw2Matrix.Matrix;
import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixTest {

    /**
     * A test to check correctness of input matrix
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNull() throws Exception {
        new Matrix(null);
    }

    /**
     * A test to check correctness of input matrix
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEven() throws Exception {
        new Matrix(new int[][] {{0, 0}, {0, 0}});
    }

    /**
     * A test to check correctness of input matrix
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNotSquare() throws Exception {
        new Matrix(new int[][] {{0, 0, 0}, {0, 0}, {0, 1, 1}});
    }

    /**
     * A tiny test to check correctness on this matrix
     */
    @Test
    public void testGetSpiralIntegersTiny() throws Exception {
        Matrix matrix = new Matrix(new int[][] { {7} });
        int[] expectedAnswer = new int[] {7};
        matrix.getSpiral();
        assertArrayEquals(expectedAnswer, matrix.getSpiral());
    }

    /**
     * A small test to check correctness of the spiral walking
     */
    @Test
    public void testGetSpiralIntegersSmall() throws Exception {
        Matrix matrix = new Matrix(new int[][] {{1, 2, 3},
                                                {4, 5, 6},
                                                {7, 8, 9}});
        int[] expectedAnswer = new int[] {5, 2, 1, 4, 7, 8, 9, 6, 3};

        assertArrayEquals(expectedAnswer, matrix.getSpiral());
    }


    /**
     * A medium-sized test to check correctness of the spiral walking with Integers
     */
    @Test
    public void testGetSpiralIntegersMedium() throws Exception {
        Matrix matrix = new Matrix(new int[][] {{4, 5, 8, 5, 6},
                                                {2, 0, 9, 6, 7},
                                                {2, 0, 4, 1, 2},
                                                {2, 5, 0, 33, 1},
                                                {6, 1, 1, 77, 5}});
        int[] expectedAnswer = new int[] {4, 9, 0, 0, 5, 0, 33, 1, 6, 5, 8, 5, 4, 2, 2, 2, 6, 1, 1, 77, 5, 1, 2, 7, 6};

        assertArrayEquals(expectedAnswer, matrix.getSpiral());
    }

    /**
     * A big test to check correctness of the spiral walking with Integers
     */
    @Test
    public void testGetSpiralIntegersBig() throws Exception {
        Matrix matrix = new Matrix(new int[][] {{4, 5, 8, 5, 6, 6, 3},
                                                {2, 0, 9, 6, 7, 8, 9},
                                                {5, 0, 4, 1, 2, 3, 4},
                                                {24, 8, 4, 11, 2, 8, 2},
                                                {2, 0, 4, 1, 2, 4, 5},
                                                {33, 5, 0, 33, 1, 6, 7},
                                                {6, 1, 1, 77, 5, 8, 9}});
        int[] expectedAnswer = new int[] {11, 1, 4, 4, 4, 1, 2, 2, 2, 7, 6, 9, 0, 0, 8, 0, 5, 0, 33, 1, 6, 4, 8, 3, 8, 6, 6, 5, 8, 5, 4, 2, 5, 24, 2, 33, 6, 1, 1, 77, 5, 8, 9, 7, 5, 2, 4, 9, 3};

        assertArrayEquals(expectedAnswer, matrix.getSpiral());
    }

    /**
     * A small test to check correctness of the sorting columns of Integers
     */
    @Test
    public void testSortIntegers1x1() throws Exception {
        Matrix matrix = new Matrix(new int[][] {{7}});
        matrix.sort();

        int[] expectedAnswer = new int[] {7};

        assertArrayEquals(expectedAnswer, matrix.getSpiral());
    }

    /**
     * A test to check correctness of the sorting columns of Integers
     */
    @Test
    public void testSortIntegers() throws Exception {
        Matrix matrix = new Matrix(new int[][] {{9, 5, 8},
                                                {2, 0, 9},
                                                {6, 1, 1}});
        matrix.sort();

        int[] expectedAnswer = new int[] {9, 8, 5, 0, 1, 1, 6, 2, 9};

        assertArrayEquals(expectedAnswer, matrix.getSpiral());
    }
}