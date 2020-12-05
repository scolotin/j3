package ru.scol.gb.j3.lesson6;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestIsContainsFalse {
    private int[] array;
    private int[] numbers;

    public TestIsContainsFalse(int[] array, int[] numbers) {
        this.array = array;
        this.numbers = numbers;
    }

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new int[][][] {
                { { 0, 2, 3, 0, 5, 6, 7, 8, 9 }, { 1, 4 } },
                { { 0, 2, 3, 0, 5, 0, 7, 0, 9 }, { 1, 4 } },
                { { 0, 2, 3, 0, 5, 6, 7, 8, 9 }, { 1, 4 } },
                { { 0, 2, 3, 0, 5, 6, 0, 8, 0 }, { 1, 4 } }
        });
    }

    @Test
    public void testIsContainsFalse() {
        Assert.assertFalse(lesson6.isContains(array, numbers));
    }
}
