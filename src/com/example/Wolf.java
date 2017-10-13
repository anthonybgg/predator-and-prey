package com.example;

import java.awt.*;

public class Wolf extends Animal {
    private static int count;
    private int daysSinceMeal;

    /**
     * Get the number of wolves.
     * @return the number of wolves.
     */
    public static int getCount() {
        return count;
    }

    /**
     * Default constructor.
     * @param cell
     */
    public Wolf(Cell cell) {
        this.weight = 10;
        this.cell = cell;
        this.color = Color.BLACK;
        this.daysSinceMeal = 0;
        count ++;
    }

    @Override
    public void dies(DeathReason deathReason) {
        this.weight = 0;
        if (count > 0) {
            count --;
        }
        else {
            count = 0;
        }
    }

    /**
     * Helper function that will check the condition for a wolf to give birth.
     */
    private void giveBirth() {
        if (weight > 50) {
            if (random.nextDouble() < 0.2) {
                Animal babyWolf = new Wolf(cell);
                count ++;
                weight -= babyWolf.weight * 2.0 ;
            }
        }
    }

    /**
     * Helper function that will try to make a predator eats a random prey.
     * @return true if the predator successfully ate his prey.
     */
    private boolean attempt() {
        boolean attempt;
        if (!isAlive()) {
            return false;
        }
        Animal prey = this.cell.getRandomCurrentAnimal();
        if ((random.nextDouble() <= daysSinceMeal * 0.05) && prey instanceof Wolf) {
            attempt = prey.attemptToEat(this);
            if (attempt) {
                daysSinceMeal = 0;
                weight += prey.weight;
                prey.dies(DeathReason.EATEN);
            } else {
                daysSinceMeal++;
                weight -= daysSinceMeal * 0.02;
                if (random.nextDouble() > daysSinceMeal * 0.05) {
                    dies(DeathReason.STARVATION);
                }
            }
        }
        return true;
    }

    @Override
    public boolean update() {
        attempt();
        giveBirth();
        return true;
    }
}
