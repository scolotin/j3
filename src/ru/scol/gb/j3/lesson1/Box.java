package ru.scol.gb.j3.lesson1;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private final ArrayList<T> capacity;
    private float weight;

    public Box() {
        this.capacity = new ArrayList<>();
        this.weight = 0.0f;
    }

    public void add(T item) {
        capacity.add(item);
        weight += item.getWeight();
    }

    public void shiftTo(Box<T> box, long count) throws ArrayIndexOutOfBoundsException {
        if (count > capacity.size()) {
            throw new ArrayIndexOutOfBoundsException("Box isn't have so many items (" + count + ")");
        }
        else {
            for (int i = 0; i < count; i++) {
                T item = capacity.get(0);
                box.add(item);
                capacity.remove(item);
                weight -= item.getWeight();
            }
        }
    }

    public float getWeight() {
        return weight;
    }

    public boolean compare(Box box) {
        return this.weight == box.getWeight();
    }
}
