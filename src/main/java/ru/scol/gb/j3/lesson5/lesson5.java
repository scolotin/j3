package ru.scol.gb.j3.lesson5;

import java.util.concurrent.CountDownLatch;

public class lesson5 {
    private static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        int tunnelBandwidth = (CARS_COUNT / 2) + 1;
        Race race = new Race(new Road(60), new Tunnel(tunnelBandwidth), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        CountDownLatch allReadyFlag = new CountDownLatch(CARS_COUNT);
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), allReadyFlag);
        }
        Thread[] participants = new Thread[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            participants[i] = new Thread(cars[i]);
            participants[i].start();
        }

        synchronized (race) {
            try {
                allReadyFlag.await();
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                race.start();
                race.notifyAll();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < cars.length; i++) {
            try {
                participants[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
