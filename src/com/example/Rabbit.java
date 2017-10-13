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

    /**
     * Default constructor.
     * @param cell
     */
    public Rabbit(Cell cell) {
        this.weight = 1.0;
        this.cell = cell;
        this.color = Color.red;
        count++;
    }

    /**
     * @return the amount of food a bunny can eat proportionally to its weight.
     */
    private double getAppetite() {
        return (this.weight + 1) / 4;
    }

    @Override
    public void dies(DeathReason deathReason) {
        this.weight = 0.0;
        if (count > 0)
            count --;
        else {
            count = 0;
        }
    }

    @Override
    public boolean update() {
        double amountEaten = 0;
        if (this.cell.getVegetationShare() > getAppetite() * 2 || this.cell.getVegetationShare() > getAppetite()) {
            this.cell.consumeVegetation(amountEaten);
            increaseWeight();
        } else {
            getHungerDeficit();
        }
        if (hungerDeficit > 0) {
            if (random.nextDouble() <= hungerDeficit / weight) {
                this.dies(DeathReason.STARVATION);
                return false;
            }
        }
        if (Math.random() < 0.01) {
            this.dies(DeathReason.NATURAL_CAUSES);
            return true;
        }
        giveBirth();
        return true;
    }

    /**
     * This helper function will verify each condition for a bunny to go hungry.
     * @return the hunger deficit
     */
    private double getHungerDeficit() {
        if (this.cell.getVegetationShare() < weight / 4) {
            weight -= weight * 0.2;
            hungerDeficit += getAppetite() - this.cell.getVegetationShare();
            return hungerDeficit;
        } else if (this.cell.getVegetationShare() < getAppetite()) {
            hungerDeficit += getAppetite() - this.cell.getVegetationShare();
            return hungerDeficit;
        } else {
            return hungerDeficit;
        }
    }

    /**
     * This helper function will verify each condition for a rabbit to increase its weight.
     * @return the weight of the rabbit.
     */
    private double increaseWeight() {
        double excess = 0.0;
        if (this.cell.getVegetationShare() > getAppetite() * 2) {
            excess += this.getAppetite() * 2;
            if (hungerDeficit > excess) {
                hungerDeficit -= excess;
            } else {
                excess -= hungerDeficit;
                hungerDeficit = 0;
                weight += excess / 2;
            }
        } else if (this.cell.getVegetationShare() > getAppetite()) {
            if (hungerDeficit > excess) {
                hungerDeficit -= excess;
            } else {
                excess -= hungerDeficit;
                hungerDeficit = 0;
                weight += excess / 2;
            }
        }
        return weight;
    }

    private void giveBirth() {
        if (this.weight > 10) {
            if (random.nextDouble() < 0.1) {
                double babyRabbit = 1 + random.nextInt(7);
                count += babyRabbit;
                this.weight -= babyRabbit;
            }
        }
    }

}
