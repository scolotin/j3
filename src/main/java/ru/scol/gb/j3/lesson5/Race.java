package ru.scol.gb.j3.lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    private ArrayList<Stage> stages;
    private boolean raceBegin = false;
    private static final AtomicInteger finishedPosition = new AtomicInteger(0);

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public boolean isRaceBegin() {
        return raceBegin;
    }

    public void start() {
        raceBegin = true;
    }

    public int finish() {
        return finishedPosition.incrementAndGet();
    }
}
