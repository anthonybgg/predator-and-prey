package com.example;


import java.awt.*;
import java.util.Random;

abstract class Animal    {
    protected static Random random = new Random();
    protected Color color;
    protected Cell cell;
    protected double weight;
    public Animal() { }

    /**
     * Check if the animal is alive.
     * @return true or false if the animal is alive.
     */
    public boolean isAlive() {
        if (this.weight > 0) {
            return true;
        } else
            return false;
    }

    /**
     * It gets the dimension of the animal.
     * @return the dimension in the cell.
     */
    public double getSize() {
        return 1 + Math.sqrt(this.weight);
    }

    /**
     * Draw the correspondent animal in a random cell based on its dimension.
     * @param brush
     */
    public void draw(Graphics brush) {
        int dimension = ((int) getSize());
        int x = this.cell.getRandomXInCell();
        int y = this.cell.getRandomYInCell();
        if (this instanceof Rabbit) {
            brush.setColor(Color.red);
            brush.fillRect(x,y, dimension, dimension);
        } else {
            brush.setColor(Color.BLACK);
            brush.fillRect(x,y, dimension, dimension);
        }
    }

    /**
     * @param predator is the Wolf.
     * @return true if the attempt to eat's equation is true.
     */
    public boolean attemptToEat(Animal predator) {
        return random.nextDouble() * predator.weight > 5 * random.nextDouble() * this.weight;
    }

    /**
     * This function will set the weight of the animal equal to zero.
     * @param deathReason reasons for an animal to die.
     */
    public void dies(DeathReason deathReason) {
        this.weight = 0.0;
    }

    public boolean update() {
        return true;
    }

}
