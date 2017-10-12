package com.example;

import java.awt.*;

public class Wolf extends Animal {
    private static int count;

    public void setDaysSinceMeal(int daysSinceMeal) {
        this.daysSinceMeal = daysSinceMeal;
    }

    private int daysSinceMeal;

    public static int getCount() {
        return count;
    }

    public Wolf(Cell cell) {
        this.weight = 10;
        this.cell = cell;
        this.color = Color.BLACK;
        this.daysSinceMeal = 0;
        count ++;
    }

    public int getDaysSinceMeal() {
        return daysSinceMeal;
    }

    public void dies(DeathReason deathReason) {
        this.weight = 0.0;
        count --;
    }

    public void giveBirth() {
        if (this.weight > 50) {
            if (random.nextDouble() < 0.2) {
                int babyWolf = 1;
                count += babyWolf;
                this.weight -= 10;
            }
        }
    }
    public boolean wolfEatsWolf() {
        if (this.daysSinceMeal > 20) {
            count --;
            return true;
        }
        return false;
    }
    public boolean update() {
        wolfEatsWolf();
        if (random.nextDouble() < this.daysSinceMeal * 0.5) {
            this.dies(DeathReason.STARVATION);
        }
        giveBirth();
        return false;
    }
}
