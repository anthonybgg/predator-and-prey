package com.example;

import java.awt.*;

public class Wolf extends Animal {
    private static int count;
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
        if (!this.attemptToEat(this)) {
            daysSinceMeal ++;
            this.weight -= this.daysSinceMeal * 0.02;
        } else
            daysSinceMeal = 0;
        return daysSinceMeal;
    }

    public int setDaysSinceMeal() {
        return daysSinceMeal = 0;
    }

    public void dies(DeathReason deathReason) {
        this.weight = 0;
        if (count > 0)
            count --;
        else
            count = 0;
    }

    public void giveBirth() {
        if (this.weight > 50) {
            if (random.nextDouble() < 0.2) {
                Animal babyWolf = new Wolf(cell);
                count ++;
                this.weight -= babyWolf.weight * 2.0 ;
            }
        }
    }

    public boolean wolfEatsWolf() {
        getDaysSinceMeal();
        if (this.daysSinceMeal > 20) {
            if (this.cell.getRandomCurrentAnimal() instanceof Wolf) {
                count --;
                daysSinceMeal = 0;
                return true;
            }
            return true;
        }
        return false;
    }
    public boolean update() {
        if (random.nextDouble() < this.daysSinceMeal * 0.5) {
            this.dies(DeathReason.STARVATION);
        }
        giveBirth();
        wolfEatsWolf();
        return false;
    }
}
