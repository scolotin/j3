package ru.scol.gb.j3.lesson5;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }

    private final Race race;
    private final int speed;
    private final String name;

    private CountDownLatch readyFlag;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public Car(Race race, int speed, CountDownLatch readyFlag) {
        this(race, speed);
        this.readyFlag = readyFlag;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            readyFlag.countDown();

        } catch (Exception e) {
            e.printStackTrace();
        }

        synchronized (race) {
            try {
                while (!race.isRaceBegin()) {
                    race.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        if (race.finish() == 1) {
            System.out.println(this.name + " выиграл гонку!");
        }
    }
}
