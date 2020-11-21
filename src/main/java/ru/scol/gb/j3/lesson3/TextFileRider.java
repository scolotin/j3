package ru.scol.gb.j3.lesson3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TextFileRider {
    private static final Scanner CONSOLE_INPUT = new Scanner(System.in);
    private static final long PAGE_SIZE = 1800L;
    public static final long START_PAGE = 0;

    private static long calcOffset(long pageNumber) {
        return pageNumber * PAGE_SIZE;
    }

    public static void main(String[] args) {
        String fileName;

        if (args.length == 1) {
            fileName = args[0];
        }
        else {
            System.out.print("Please enter file name: ");
            fileName = CONSOLE_INPUT.next();
        }

        File file = new File(fileName);
        while (!file.exists() && !file.isFile()) {
            System.out.println("File " + fileName + " not found. Please enter file name or 'q' for exit ");
            fileName = CONSOLE_INPUT.next();
            if (fileName.equals("q")) {
                System.exit(0);
            }
            file = new File(fileName);
        }

        long fileSize = file.length();
        long pageCount = (fileSize % PAGE_SIZE == 0) ? fileSize / PAGE_SIZE : (fileSize / PAGE_SIZE) + 1;
        long pageNumber;

        System.out.printf("Enter number page 0-%d: ", pageCount);
        pageNumber = CONSOLE_INPUT.nextLong();
        if (pageNumber > pageCount) {
            System.out.println("Incorrect interred page number - " + pageNumber + " Read start at page " + START_PAGE);
            System.out.println();
            pageNumber = START_PAGE;
        }

        try (RandomAccessFile readFile = new RandomAccessFile(file, "r")) {
            String controlChar;
            do {
                readFile.seek(calcOffset(pageNumber));
                byte[] pageData = new byte[(int) PAGE_SIZE];
                int readCount;
                readCount = readFile.read(pageData);
                System.out.print(new String(pageData, 0, readCount, StandardCharsets.UTF_8));

                System.out.print("\n\nIs next page? Enter 'n' - next page, 'p' - previous page or 'q' - exit: ");
                controlChar = CONSOLE_INPUT.next();
                if (controlChar.equals("n")) {
                    pageNumber++;
                }
                else if (controlChar.equals("p")) {
                    pageNumber--;
                }
            } while (!controlChar.equals("q"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
