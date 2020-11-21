package ru.scol.gb.j3.lesson3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class lesson3 {
    private static final String PATH_TO_WORK_DIR = "src/main/java/ru/scol/gb/j3/lesson3/";
    private static final int FILE_COUNT = 5;

    public static void main(String[] args) {
        /* Task 1 */
        byte[] arr = new byte[50];
        try (FileInputStream inputStream = new FileInputStream(PATH_TO_WORK_DIR + "input.txt")) {
            if (inputStream.read(arr) != -1) {
                System.out.println(Arrays.toString(arr));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        /* Task 2 */
        try {
            ArrayList<InputStream> files = new ArrayList<>();
            for (int i = 1; i <= FILE_COUNT; i++) {
                files.add(new FileInputStream(PATH_TO_WORK_DIR + String.format("file%s.txt", i)));
            }
            SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(files));

            FileOutputStream outputFile = new FileOutputStream(PATH_TO_WORK_DIR + "output.txt");
            int x;
            while ((x = sequenceInputStream.read()) != -1) {
                outputFile.write(x);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
