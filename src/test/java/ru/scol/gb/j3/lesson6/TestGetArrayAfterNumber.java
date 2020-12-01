package ru.scol.gb.j3.lesson6;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestGetArrayAfterNumber {
    private int[] expectedArray;
    private int[] array;
    private int number;

    public TestGetArrayAfterNumber(int[] expectedArray, int[] array, int[] number) {
        this.expectedArray = expectedArray;
        this.array = array;
        this.number = number[0];
    }

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new int[][][] {
                { { 4, 7, 11, 13 }, { 1, 3, 4, 7, 11, 13 }, { 4 } },
                { { 4, 7, 9, 8 }, { 1, 4, 7, 9, 8 }, { 4 } },
                { { 4, 1, 3, 7, 11, 13 }, { 4, 1, 3, 7, 11, 13 }, { 4 } },
                { { 4 }, { 1, 3, 4, 7, 11, 4 }, { 4 } },
                { { 4 }, { 4, 4, 4, 4, 4, 4 }, { 4 } }
        });
    }

    @Test
    public void testReturnCorrectData() {
        Assert.assertArrayEquals(expectedArray, lesson6.getArrayAfterNumber(array, number));
    }
}
