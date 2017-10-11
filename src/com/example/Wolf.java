package com.example;

public class Wolf extends Animal {
    private static int count;
    private int daysSinceMeal;

    public static int getCount() {
        return count;
    }

    public Wolf(Cell cell) {

    }

    public int getDaysSinceMeal() {
        return daysSinceMeal;
    }

    @Override
    public void dies(DeathReason deathReason) {

    }

    public boolean update() {
        return false;
    }
}
