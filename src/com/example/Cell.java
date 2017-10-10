package com.example;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Cell extends GraphicalSimulation {
    protected static Random random = new Random();
    private PredatorPreySimulation grid;
    private final int x, y;
    private final int cellWidth;
    private final int cellHeight;
    private List<Animal> currentAnimals;
    private List<Animal> nextAnimals;
    private double vegetation, vegetationShare;
    private double proportionalGrowthRate, maximumVegetation, linearGrowthRate;

    public Cell() {

    }
    public Cell(Cell cells[][], int x, int y, int cellWidth, int cellHeight, double vegetation, double maximumVegetation,
                double proportionalGrowthRate, double linearGrowthRate) {

    }

    public void addAnimals(Animal animal) {

    }

    public Animal getRandomCurrentAnimal() {

    }

    public void copyNextAnimalsToCurrent() {

    }

    public void update() {

    }

    public double getVegetationShare() {

    }

    public void consumeVegetation(double amountEaten) {

    }

    public void draw(Graphics brush) {

    }

    public Color getBackgroundColor() {

    }

    public int getRandomXInCell(int x) {

    }

    public int getRandomYInCell() {

    }

    @Override
    public void paint(Graphics brush) {

    }

}
