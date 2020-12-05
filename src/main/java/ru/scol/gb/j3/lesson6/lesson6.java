package ru.scol.gb.j3.lesson6;

import java.util.Arrays;

public class lesson6 {
    public static int[] getArrayAfterNumber(int[] array, int number) throws RuntimeException {
        int position = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                position = i;
            }
        }
        if (position == -1) {
            throw new RuntimeException(String.format("Number (%d) not found in array", number));
        }
        return Arrays.copyOfRange(array, position, array.length);
    }

    public static boolean isContains(int[] array, int[] numbers) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (array[i] == numbers[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
