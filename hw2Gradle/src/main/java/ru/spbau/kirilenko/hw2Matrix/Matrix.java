package ru.spbau.kirilenko.hw2Matrix;

import java.util.Arrays;
import java.util.ArrayList;

/** A class that stores an odd square matrix of integers and allows to sort it and get it in spiral order
 *
 */
public class Matrix {
    private int[][] matrix;
    private int size;

    /**
     * Constructs a matrix from input array of COLUMNS, matrix that must be odd and square
     *
     * @param inputMatrix odd square matrix
     */
    public Matrix(int[][] inputMatrix) {
        if (inputMatrix.length % 2 == 0) {
            throw new IllegalArgumentException("N is even!");
        }
        for (int[] col : inputMatrix) {
            if (col.length != inputMatrix.length) {
                throw new IllegalArgumentException("Not NxN matrix");
            }
        }
        size = inputMatrix.length;
        matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = inputMatrix[j][i];
            }
        }
    }

    /**
     * This method sorts a columns of the matrix and order them by increasing of the first elements of the columns
     */
    public void sort() {
        Arrays.sort(this.matrix, (int[] col1, int[] col2)->{ return Integer.compare(col1[0], col2[0]) ; } );
    }

    /**
     * This method walks around the matrix from it's center NON-clockwise and returns elements in spiral order
     * @return one-dimensional array of size n * n that contains elements in spiral order
     */
    public int[] getSpiral() {
        int xs = size / 2, ys = size / 2, currentLength = 1, position = 0;
        boolean type = false;
        int[] answer = new int[size * size];
        answer[position++] = matrix[xs][ys];
        currentLength += 2;
        xs--;
        while (position < size * size) {
            if (!type) {
                getVerticalLineDown(xs, ys, currentLength / 2 + 1, answer, position);
                ys -= currentLength / 2;
                position += currentLength / 2 + 1;
                xs++;
                getHorizontalLineLeft(xs, ys, currentLength / 2, answer, position);
                xs += currentLength / 2;
                position += currentLength / 2;
            }
            else {
                getVerticalLineUp(xs, ys, currentLength / 2 + 1, answer, position);
                ys += currentLength / 2;
                position += currentLength / 2 + 1;
                xs--;
                getHorizontalLineRight(xs, ys, currentLength / 2, answer, position);
                xs -= currentLength / 2;
                position += currentLength / 2;
            }
            type = !type;
            currentLength += 2;
        }
        return answer;
    }

    private void getHorizontalLineLeft(int x, int y, int len, int[] answer, int position) {
        for (int i = 0; i < len; i++) {
            answer[position++] = matrix[y][x + i];
        }
    }

    private void getHorizontalLineRight(int x, int y, int len, int[] answer, int position) {
        for (int i = 0; i < len; i++) {
            answer[position++] = matrix[y][x - i];
        }
    }

    private void getVerticalLineDown(int x, int y, int len, int[] answer, int position) {
        for (int i = 0; i < len; i++) {
            answer[position++] = matrix[y - i][x];
        }
    }

    private void getVerticalLineUp(int x, int y, int len, int[] answer, int position) {
        for (int i = 0; i < len; i++) {
            answer[position++] = matrix[y + i][x];
        }
    }
}
