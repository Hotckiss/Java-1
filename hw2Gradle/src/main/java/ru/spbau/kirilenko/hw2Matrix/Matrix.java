package ru.spbau.kirilenko.hw2Matrix;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A class that stores an odd square matrix of integers and allows to sort it and get it in spiral order
 */
public class Matrix {
    private int[][] matrix;

    private enum Direction {
        DownLeft, UpRight
    };

    /**
     * Constructs a matrix from input array of COLUMNS, matrix that must be odd and square
     *
     * @param inputMatrix odd square matrix
     */
    public Matrix(int[][] inputMatrix) {
        if(inputMatrix == null) {
            throw new IllegalArgumentException("Not NxN matrix");
        }
        if (inputMatrix.length % 2 == 0) {
            throw new IllegalArgumentException("N is even!");
        }
        for (int[] col : inputMatrix) {
            if (col.length != inputMatrix.length) {
                throw new IllegalArgumentException("Not NxN matrix");
            }
        }
        matrix = new int[inputMatrix.length][inputMatrix.length];
        for (int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix.length; j++) {
                matrix[i][j] = inputMatrix[j][i];
            }
        }
    }

    /**
     * This method sorts a columns of the matrix and order them by increasing of the first elements of the columns
     */
    public void sort() {
        Arrays.sort(this.matrix, Comparator.comparingInt(col -> col[0]) );
    }

    /**
     * This method walks around the matrix from it's center counterclockwise and returns elements in spiral order
     * @return one-dimensional array of size n * n that contains elements in spiral order
     */
    public int[] getSpiral() {
        int xCenter = matrix.length / 2;
        int yCenter = matrix.length / 2;
        int currentLength = 1;
        int position = 0;

        Direction direction = Direction.DownLeft;

        int[] answer = new int[matrix.length * matrix.length];

        answer[position++] = matrix[xCenter][yCenter];
        currentLength += 2;
        xCenter--;
        while (position < matrix.length * matrix.length) {
            if (direction == Direction.DownLeft) {
                getVerticalLineDown(xCenter, yCenter, currentLength / 2 + 1, answer, position);
                yCenter -= currentLength / 2;
                position += currentLength / 2 + 1;
                xCenter++;
                getHorizontalLineLeft(xCenter, yCenter, currentLength / 2, answer, position);
                xCenter += currentLength / 2;
                position += currentLength / 2;
                direction = Direction.UpRight;
            }
            else {
                getVerticalLineUp(xCenter, yCenter, currentLength / 2 + 1, answer, position);
                yCenter += currentLength / 2;
                position += currentLength / 2 + 1;
                xCenter--;
                getHorizontalLineRight(xCenter, yCenter, currentLength / 2, answer, position);
                xCenter -= currentLength / 2;
                position += currentLength / 2;
                direction = Direction.DownLeft;
            }
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
