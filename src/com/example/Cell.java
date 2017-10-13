package com.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cell {
    private static Random random = new Random();
    private PredatorPreySimulation predatorPreySimulation;
    private int x, y;

    private int cellWidth;
    private int cellHeight;
    private List<Animal> currentAnimals = new ArrayList<>();
    private List<Animal> nextAnimals = new ArrayList<>();
    private double vegetation = 0, vegetationShare = 0;
    private double proportionalGrowthRate, maximumVegetation, linearGrowthRate;

    /**
     * This is a default constructor of Cell.
     * @param predatorPreySimulation
     * @param x
     * @param y
     * @param cellWidth is the width of the Cell.
     * @param cellHeight is the Height of the Cell.
     * @param vegetation is the total vegetation.
     * @param maximumVegetation is the maximum amount that a cell can reach when vegetation grows.
     * @param proportionalGrowthRate is the proportional growth rate.
     * @param linearGrowthRate is the linear growth rate.
     */
    public Cell(PredatorPreySimulation predatorPreySimulation, int x, int y, int cellWidth, int cellHeight,
                double vegetation, double maximumVegetation, double proportionalGrowthRate, double linearGrowthRate) {
        this.x = x;
        this.y = y;
        this.cellHeight = cellHeight;
        this.cellWidth = cellWidth;
        this.vegetation = vegetation;
        this.maximumVegetation = maximumVegetation;
        this.proportionalGrowthRate = proportionalGrowthRate;
        this.linearGrowthRate = linearGrowthRate;
        this.predatorPreySimulation = predatorPreySimulation;
    }

    public void addAnimal(Animal animal) {
            nextAnimals.addAll(currentAnimals);
    }

    /**
     * Get a random animal in the list.
     * @return a random animal in the list.
     */
    public Animal getRandomCurrentAnimal() {
        return currentAnimals.get(random.nextInt(currentAnimals.size()));
    }

    /**
     * This function copies every animal in the next list to the current list.
     */
    public void copyNextAnimalsToCurrent() {
        for (Animal animal : nextAnimals) {
            currentAnimals.add(animal);
            nextAnimals.remove(animal);
        }
    }

    /**
     * Update the cell every epoch.
     */
    public void update() {
        vegetation = vegetation * (proportionalGrowthRate + 1) + linearGrowthRate;
        if (vegetation >= maximumVegetation) {
            vegetation = maximumVegetation;
        }
        for (Animal animal : currentAnimals) {
            animal.update();
        }
    }

    /**
     * @return the amount of shared vegetation.
     */
    public double getVegetationShare() {
        vegetationShare = (0.5 * this.vegetation) / Rabbit.getCount();
        return vegetationShare;
    }

    /**
     * Subtract from the total to the amount of vegetation eaten by the rabbits
     * @param amountEaten is the total amount of vegetation that every rabbit ate.
     */
    public void consumeVegetation(double amountEaten) {
        double weight = getARabbit().weight;
        amountEaten = (weight + 1) / 4;
        vegetationShare = getVegetationShare();
        if (vegetationShare > amountEaten * 2) {
            vegetation -= amountEaten * 2;
        } else if (vegetationShare > amountEaten) {
            vegetation -= amountEaten;
        }
    }

    /**
     * Helper function to get a bunny.
     * @return get a bunny in the current list of animals.
     */
    private Animal getARabbit() {
        if (getRandomCurrentAnimal() instanceof Rabbit) {
            return getRandomCurrentAnimal();
        } else {
            return getARabbit();
        }
    }

    /**
     *
     * @param brush
     */
    public void draw(Graphics brush) {
        for (Animal animal : currentAnimals) {
            animal.draw(brush);
        }
    }

    /**
     * Colors the background in function of the amount of vegetation available.
     * @return a color.
     */
    public Color getBackgroundColor() {
        if (vegetation > maximumVegetation) {
            vegetation = maximumVegetation;
        }
        if (vegetation < maximumVegetation / 2) {
            return Color.YELLOW;
        } else {
            return Color.GREEN;
        }
    }

    /**
     * @return a random position in the cell that every animal should be drawn.
     */
    public int getRandomXInCell() {
        x = random.nextInt(cellWidth);
        return x;
    }

    /**
     * @return a random position in the cell that every animal should be drawn.
     */
    public int getRandomYInCell() {
        y = random.nextInt(cellHeight);
        return y;
    }

    /**
     * This function should paint the back based on the amount of vegetation available.
     * @param brush is setting the color.
     */
    public void paint(Graphics brush) {
        brush.setColor(Color.darkGray);
    }

}
