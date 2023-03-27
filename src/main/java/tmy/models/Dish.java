package tmy.models;

import java.time.Duration;

public final class Dish {
    private int id;
    private String name;
    private Recipe recipe;
    private float price;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public float getPrice() {
        return price;
    }

    public Duration getDuration() {
        return recipe.getCookingTime();
    }
}
