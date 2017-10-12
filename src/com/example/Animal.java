package com.example;

import java.awt.*;
import java.util.Random;

abstract class Animal    {
    protected static Random random = new Random();
    protected Color color;
    protected Cell cell;
    protected double weight;
    public int daysSinceMeal;

    public Animal() {

    }

    public boolean isAlive(Cell cell) {
        if (this.weight > 0) {
            return true;
        } else
            return false;
    }

    public double getSize(double weight) {
        weight = 1 + Math.sqrt(this.weight);
        return weight;
    }

    public void draw(Graphics brush) {

    }

    public double getWeightRabbit() {
        if (this instanceof Rabbit) {
            return this.weight;
        }
        else
            return 0.0;
    }
    public boolean attemptToEat(Wolf predator) {
        boolean attempt = true;
        if ((random.nextDouble() <= predator.getDaysSinceMeal() * 0.05 || this instanceof Rabbit) && this != predator) {
            if (random.nextDouble() * predator.weight > 5 * random.nextDouble() * this.weight) {
                return attempt;
            }
        }
        return !attempt;
    }

    public void dies(DeathReason deathReason) {

    }

    public boolean update() {

        return false;
    }

}
