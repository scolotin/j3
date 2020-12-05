package ru.scol.gb.j3.lesson6;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestIsContainsTrue {
    private int[] array;
    private int[] numbers;

    public TestIsContainsTrue(int[] array, int[] numbers) {
        this.array = array;
        this.numbers = numbers;
    }

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new int[][][] {
                { { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 4 } },
                { { 1, 2, 3, 0, 5, 6, 7, 8, 9 }, { 1, 4 } },
                { { 0, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 4 } },
                { { 0, 2, 3, 0, 5, 6, 1, 8, 0 }, { 1, 4 } }
        });
    }

    @Test
    public void testIsContainsTrue() {
        Assert.assertTrue(lesson6.isContains(array, numbers));
    }
}
