package com.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cell {
    private static Random random = new Random();
    private PredatorPreySimulation predatorPreySimulation;
    private Cell[][] cells;
    private int x, y;
    private int cellWidth;
    private int cellHeight;
    private List<Animal> currentAnimals = new ArrayList<>();
    private List<Animal> nextAnimals = new ArrayList<>();
    private double vegetation, vegetationShare;
    private double proportionalGrowthRate, maximumVegetation, linearGrowthRate;


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
        for (Animal eachAnimal : currentAnimals) {
            this.nextAnimals.add(eachAnimal);
        }
    }

    public Animal getRandomCurrentAnimal() {
        return currentAnimals.get(random.nextInt(currentAnimals.size()));
    }

    public void copyNextAnimalsToCurrent() {
        List<Animal> currentAnimals = new ArrayList<>();
        for (int i = 0; i < this.nextAnimals.size(); i++) {
            currentAnimals.add(this.nextAnimals.get(i));
            this.nextAnimals.remove(i);
        }
    }

    public void update() {
        double amountEaten = 0;
        consumeVegetation(amountEaten);
        vegetation = vegetation * (proportionalGrowthRate + 1) + linearGrowthRate;
        for (int i = 0; i < currentAnimals.size(); i++) {
            if (currentAnimals.get(i) instanceof Wolf) {
                Wolf wolf = (Wolf) currentAnimals.get(i);
                wolf.update();
            } else {
                Rabbit rabbit = (Rabbit) currentAnimals.get(i);
                rabbit.update();
            }
        }
    }

    public double getVegetationShare() {
        vegetationShare = (0.5 * this.vegetation) / Rabbit.getCount();
        return vegetationShare;
    }


    public void consumeVegetation(double amountEaten) {
        for (Animal rabbit: currentAnimals) {
            if (rabbit instanceof Rabbit) {
                getVegetationShare();
                amountEaten += ((Rabbit) rabbit).getAppetite();
            }
        }
        vegetation -= amountEaten;
    }

    public void draw(Graphics brush) {
        paint(brush);
    }

    public Color getBackgroundColor() {

        return Color.GREEN;
    }

    public int getRandomXInCell() {
        this.x = random.nextInt(cellWidth);
        return this.x;
    }

    public int getRandomYInCell() {
        this.y = random.nextInt(cellHeight);
        return this.y;
    }

    public void paint(Graphics brush) {

        brush.setColor(Color.green);
        getBackgroundColor();
    }

}
