package ru.scol.gb.j3.lesson4;

public class lesson4 {
    public static void main(String[] args) {
        /* Task 1 */
        PrintSymbol printSymbol = new PrintSymbol();

        Thread printAThread = new Thread(printSymbol::printA);
        Thread printBThread = new Thread(printSymbol::printB);
        Thread printCThread = new Thread(printSymbol::printC);

        printAThread.start();
        printBThread.start();
        printCThread.start();

        /* Task 2 */
        MFU mfu = new MFU("HP 5200 Series");
        System.out.println(mfu.welcome());

        Thread user1Thread = new Thread(() -> {
            try {
                mfu.print("User 1", 10);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread user2Thread = new Thread(()-> {
            try {
                mfu.scan("User 2", 7);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread user3Thread = new Thread(()-> {
            try {
                mfu.print("User 3", 20);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread user4Thread = new Thread(()-> {
            try {
                mfu.copy("User 4", 4);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        user1Thread.start();
        user2Thread.start();
        user3Thread.start();
        user4Thread.start();
    }

    private static class PrintSymbol {
        private static volatile String currentSymbol = "A";
        private static final int LOOP_COUNT = 5;

        private void printA() {
            synchronized (this) {
                try {
                    for (int i = 0; i < LOOP_COUNT; i++) {
                        while (!currentSymbol.equals("A")) {
                            wait();
                        }
                        System.out.print(currentSymbol);
                        currentSymbol = "B";
                        notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void printB() {
            synchronized (this) {
                try {
                    for (int i = 0; i < LOOP_COUNT; i++) {
                        while (!currentSymbol.equals("B")) {
                            wait();
                        }
                        System.out.print(currentSymbol);
                        currentSymbol = "C";
                        notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void printC() {
            synchronized (this) {
                try {
                    for (int i = 0; i < LOOP_COUNT; i++) {
                        while (!currentSymbol.equals("C")) {
                            wait();
                        }
                        System.out.print(currentSymbol);
                        currentSymbol = "A";
                        notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
