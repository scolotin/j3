package ru.scol.gb.j3.lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class lesson1 {
    private static <T> void swapElement(T[] arr, int from, int to) {
        T temp = arr[to];
        arr[to] = arr[from];
        arr[from] = temp;
    }

    private static <T> ArrayList<T> arrayToArrayList(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

    public static void main(String[] args) {
        String[] testArray = { "hello", "from", "string", "array", "!" };

        System.out.println("Before swap: " + Arrays.toString(testArray));
        swapElement(testArray, 0,testArray.length - 1);
        System.out.println("After swap: " + Arrays.toString(testArray));

        ArrayList<String> stringArrayList = arrayToArrayList(testArray);
        System.out.println("ArrayList: " + stringArrayList.toString());

        Box<Apple> appleBox = new Box<>();
        Box<Apple> appleBox1 = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        for (int i = 0; i < 13; i++) {
            appleBox.add(new Apple());
            orangeBox.add(new Orange());
        }

        System.out.println("Apple box #0 weight before shift: " + appleBox.getWeight());
        System.out.println("Apple box #1 weight before shift: " + appleBox1.getWeight());
        appleBox.shiftTo(appleBox1, 3);
        System.out.println("Apple box #0 weight after shift: " + appleBox.getWeight());
        System.out.println("Apple box #1 weight after shift: " + appleBox1.getWeight());
        System.out.println("Orange box weight: " + orangeBox.getWeight());
        System.out.println("Is apple box weight equal orange box: " + appleBox.compare(orangeBox));
    }
}
