package com.example;

import java.awt.*;
import java.util.Random;

abstract class Animal extends Cell {
    protected static Random random = new Random();
    private Color color;
    private Cell cell;
    private double weight;

    public Animal () {

    }
    public Animal(Cell cell, Color color, double weight) {

    }

    public boolean isAlive() {
        return false;
    }

    public double getSize(double weight) {
        return weight;
    }

    @Override
    public void draw(Graphics brush) {

    }

    public boolean attemptToEat(Animal predator) {
        return false;
    }

    public void dies(DeathReason deathReason) {

    }

    @Override
    public boolean update() {
        return false;
    }

}
