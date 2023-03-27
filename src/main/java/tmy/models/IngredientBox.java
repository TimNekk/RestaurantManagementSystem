package tmy.models;

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

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public void reduceAmount(int amount) {
        if (this.amount - amount < 0) {
            throw new IllegalArgumentException("Amount of ingredient will be less than 0");
        }

        this.amount -= amount;
    }
}
