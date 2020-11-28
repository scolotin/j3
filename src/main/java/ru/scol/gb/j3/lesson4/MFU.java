package ru.scol.gb.j3.lesson4;

public class MFU {
    private final String name;
    private final Object scanLock = new Object();
    private final Object printLock = new Object();

    public MFU(String name) {
        this.name = name;
    }

    public String welcome() {
        return "MFU: " + name + " start";
    }

    public void scan(String userName, int pageCount) throws InterruptedException {
        synchronized (scanLock) {
            System.out.println(userName + " scans " + pageCount + " pages");
            for (int i = 0; i < pageCount; i++) {
                Thread.sleep(500);
            }
            System.out.println(userName + " ends scanning");
        }
    }

    public void copy(String userName, int pageCount) throws InterruptedException {
        scan(userName, pageCount);
        print(userName, pageCount);
    }

    public void print(String userName, int pageCount) throws InterruptedException {
        synchronized (printLock) {
            System.out.println(userName + " prints " + pageCount + " pages");
            for (int i = 0; i < pageCount; i++) {
                Thread.sleep(100);
            }
            System.out.println(userName + " ends printing");
        }
    }
}
