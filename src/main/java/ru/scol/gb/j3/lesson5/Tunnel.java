package ru.scol.gb.j3.lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final Semaphore bandwidth;

    public Tunnel(int bandwidth) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.bandwidth = new Semaphore(bandwidth);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                bandwidth.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
                bandwidth.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}