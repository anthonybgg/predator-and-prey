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
        this.color = Color.YELLOW;
        count++;
    }

    public double getAppetite() {
        double appetite = (this.weight + 1) / 4;
        return appetite;
    }

    public void dies(DeathReason deathReason) {
        this.weight = 0.0;
        if (count > 0)
            count --;
        else
            count = 0;
    }

    public boolean update() {
        eatEnough();
        if (hungerDeficit > 0) {
            if (random.nextDouble() <= hungerDeficit / weight) {
                this.dies(DeathReason.STARVATION);
            }
        }
        if (Math.random() < 0.1) {
            this.dies(DeathReason.NATURAL_CAUSES);
            return true;
        }
        giveBirth();
        return false;
    }
//calculate this rabbit eats consumption
    public void eatEnough() {
        double excess = 0.0;
        if (this.cell.getVegetationShare() > this.getAppetite() * 2) {
            excess += this.getAppetite() * 2;
            if (hungerDeficit > excess) {
                hungerDeficit -= excess;
            } else
                excess -= hungerDeficit;
                hungerDeficit = 0;
                this.weight += excess / 2;

        } else if (this.cell.getVegetationShare() > this.getAppetite()) {
            excess += this.cell.getVegetationShare() - this.getAppetite();
            if (hungerDeficit > excess) {
                hungerDeficit -= excess;
            } else {
                excess -= hungerDeficit;
                hungerDeficit = 0;
                this.weight += excess / 2;
            }
        } else if (this.cell.getVegetationShare() < (this.weight) / 4) {
            this.weight -= this.weight * 0.2;
            hungerDeficit += this.getAppetite() - this.cell.getVegetationShare();
        } else
            hungerDeficit += this.getAppetite() - this.cell.getVegetationShare();
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
