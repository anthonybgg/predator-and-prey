package com.example;

import java.awt.*;
import java.util.Random;

abstract class Animal    {
    protected static Random random = new Random();
    protected Color color;
    protected Cell cell;
    protected double weight;

    public Animal() {

    }

    public boolean isAlive(Cell cell) {
        if (this.weight > 0.1) {
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

    public boolean attemptToEat(Wolf predator) {
        boolean attempt = false;
        if ((random.nextDouble() <= predator.getDaysSinceMeal() * 0.05 || this instanceof Rabbit) && this != predator) {
            attempt = random.nextDouble() * predator.weight > 5 * random.nextDouble() * this.weight;
        }
        if (attempt) {
            predator.setDaysSinceMeal();
            predator.weight += this.weight;
            this.dies(DeathReason.EATEN);
        }
        return attempt;
    }

    public void dies(DeathReason deathReason) {

    }

    public boolean update() {

        return false;
    }

}
