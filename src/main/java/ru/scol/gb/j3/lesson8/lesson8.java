package ru.scol.gb.j3.lesson8;

import java.util.Arrays;

public class lesson8 {
    private static final int X_SIZE = 7;
    private static final int Y_SIZE = 7;

    public static void main(String[] args) {
        int[][] field = new int[X_SIZE][Y_SIZE];

        spiralFillArray(field);

        for (int[] row : field) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static boolean isFreeCell(int[][] array, int x, int y) {
        return array[x][y] == 0;
    }

    private static void spiralFillArray(int[][] array) {
        int xSize = array.length;
        int ySize = array[0].length;
        int totalCount = xSize * ySize;

        int x = 0;
        int y = 0;
        int cycle = 0;
        int count = 1;
        while (count <= totalCount) {
            for (int i = cycle; i < ySize - cycle && isFreeCell(array, x, i); i++) {
                y = i;
                array[x][y] = count++;
            }

            for (int i = 1 + cycle; i < xSize - cycle && isFreeCell(array, i, y); i++) {
                x = i;
                array[x][y] = count++;
            }

            for (int i = y - 1; i >= cycle && isFreeCell(array, x, i); i--) {
                y = i;
                array[x][y] = count++;
            }

            for (int i = x - 1; i >= 1 + cycle && isFreeCell(array, i, y); i--) {
                x = i;
                array[x][y] = count++;
            }

            cycle++;
        }
    }
}
