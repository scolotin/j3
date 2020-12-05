package ru.scol.gb.j3.lesson6;

import org.junit.Assert;
import org.junit.Test;

public class TestGetArrayAfterNumberException {
    @Test(expected = RuntimeException.class)
    public void testException() {
        int[] zeroArray = new int[17];
        int number = 4;

        Assert.assertArrayEquals(zeroArray, lesson6.getArrayAfterNumber(zeroArray, number));
    }
}
