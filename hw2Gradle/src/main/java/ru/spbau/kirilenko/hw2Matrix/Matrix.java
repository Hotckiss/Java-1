package ru.spbau.kirilenko.hw2Matrix;

import java.util.Arrays;
import java.util.ArrayList;

/** A class that stores an odd square matrix of comparable objects and allows to sort it and get it in spiral order
 *
 * @param <T> type of stored elements
 */
public class Matrix<T extends Comparable<T>> {
    private T[][] matrix;
    private int size;

    /**
     * Constructs a matrix from input array of COLUMNS, matrix that must be odd and square
     *
     * @param inputMatrix odd square matrix
     */
    public Matrix(T[][] inputMatrix) {
        if (inputMatrix.length % 2 == 0) {
            throw new IllegalArgumentException("N is even!");
        }
        for (T[] col : inputMatrix) {
            if (col.length != inputMatrix.length) {
                throw new IllegalArgumentException("Not NxN matrix");
            }
        }
        size = inputMatrix.length;
        matrix = inputMatrix;
    }

    /**
     * This method sorts a columns of the matrix and order them by increasing of the first elements of the columns
     */
    public void sort() {
        Arrays.sort(this.matrix, (T[] col1, T[] col2)->{ return col1[0].compareTo(col2[0]); } );
    }

    /**
     * This method walks around the matrix from it's center NON-clockwise and returns elements in spiral order
     * @return one-dimensional array of size n * n that contains elements in spiral order
     */
    public T[] getSpiral() {
        int xs = size / 2, ys = size / 2, currentLength = 1;
        ArrayList<T> answer = new ArrayList<>();
        boolean type = false;

        answer.add(matrix[xs][ys]);
        currentLength += 2;
        xs--;
        while (answer.size() < size * size) {
            if (!type) {
                getVerticalLineDown(xs, ys, currentLength / 2 + 1, answer);
                ys -= currentLength / 2;
                xs++;
                getHorizontalLineLeft(xs, ys, currentLength / 2, answer);
                xs += currentLength / 2;
            }
            else {
                getVerticalLineUp(xs, ys, currentLength / 2 + 1, answer);
                ys += currentLength / 2;
                xs--;
                getHorizontalLineRight(xs, ys, currentLength / 2, answer);
                xs -= currentLength / 2;
            }
            type = !type;
            currentLength += 2;
        }

        return answer.toArray(matrix[0]);
    }

    private void getHorizontalLineLeft(int x, int y, int len, ArrayList<T> answer) {
        for (int i = 0; i < len; i++) {
            answer.add(matrix[y][x + i]);
        }
    }

    private void getHorizontalLineRight(int x, int y, int len, ArrayList<T> answer) {
        for (int i = 0; i < len; i++) {
            answer.add(matrix[y][x - i]);
        }
    }

    private void getVerticalLineDown(int x, int y, int len, ArrayList<T> answer) {
        for (int i = 0; i < len; i++) {
            answer.add(matrix[y - i][x]);
        }
    }

    private void getVerticalLineUp(int x, int y, int len, ArrayList<T> answer) {
        for (int i = 0; i < len; i++) {
            answer.add(matrix[y + i][x]);
        }
    }

    public static void main(String[] args) {
        Matrix<Integer> spiral = new Matrix(new Integer[][] {{2, 5, 1, 4, 3},
                {4, 5, 6, 3, 2},
                {0, 0, 7, 8, 9},
                {1, 5, 3, 2, 9},
                {7, 6, 3, 4, 4}});
        Integer[] expectedResult = new Integer[] {7, 0, 5, 6, 3, 8, 2, 3, 5, 1, 0, 4, 2, 5, 1, 4, 3, 2, 9, 9, 4, 4, 3, 6, 7};
        Integer[] findResult = spiral.getSpiral();
        for (int i = 0; i < 25; i++) {
            if(expectedResult[i] != findResult[i]) {
                System.out.println("wa: " + i);
            }
        }

    }
}
