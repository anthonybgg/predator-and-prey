package com.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rabbit extends Animal {
    private static int count;
    private double hungerDeficit;

    public static int getCount() {
        return count;
    }

    public Rabbit(Cell cell) {
        this.weight = 1.0;
        this.cell = cell;
        this.color = Color.darkGray;
        count++;
    }

    public double getAppetite() {
        double appetite = (this.weight + 1) / 4;
        return appetite;
    }

    public double getWeight() {
        double weight = this.weight;
        return weight;
    }

    public double getHungerDeficit() {
        double hungerDeficit;
        hungerDeficit = this.getAppetite() - cell.getVegetationShare();
        this.hungerDeficit += hungerDeficit;
        return this.hungerDeficit;
    }

    public void dies(DeathReason deathReason) {
        this.weight = 0.0;
        count --;
    }

    public boolean update() {
        if (hungerDeficit > 0) {
            if (random.nextDouble() <= hungerDeficit / weight) {
                this.dies(DeathReason.STARVATION);
            }
        }
        if (Math.random() < 0.1) {
            this.dies(DeathReason.NATURAL_CAUSES);
            return true;
        }
        this.weight += this.cell.consumeVegetationByRabbit();
        giveBirth();
        return false;
    }

    public void giveBirth() {
        if (this.weight > 10) {
            if (random.nextDouble() < 0.1) {
                int babyRabbit = 1 + random.nextInt(7);
                count += babyRabbit;
                this.weight -= 1;
            }
        }
    }
}
