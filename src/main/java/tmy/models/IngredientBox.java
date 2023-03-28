package tmy.models;

import java.io.IOException;

public final class IngredientBox {
    private Ingredient ingredient;
    private int amount;

    public IngredientBox() {
    }

    public IngredientBox(Ingredient ingredient, int amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getAmount() {
        return amount;
    }

    /**
     * Adds the amount of the ingredient to the box.
     * @param amount amount to be added.
     */
    public void addAmount(int amount) {
        this.amount += amount;
    }

    /**
     * Reduces the amount of the ingredient in the box.
     * @param amount the amount to reduce by.
     * @throws IllegalArgumentException if there are not enough ingredients in the box.
     */
    public void reduceAmount(int amount) throws IllegalArgumentException {
        if (this.amount - amount < 0) {
            throw new IllegalArgumentException("Amount of ingredient will be less than 0");
        }

        this.amount -= amount;
    }
}
