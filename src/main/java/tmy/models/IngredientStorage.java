package tmy.models;

import java.util.List;
import java.util.Optional;

public final class IngredientStorage {
    private final List<IngredientBox> ingredientBoxes;

    public IngredientStorage(List<IngredientBox> ingredientBoxes) {
        this.ingredientBoxes = ingredientBoxes;
    }

    public Optional<Ingredient> getIngredient(int ingredientId) {
        for (IngredientBox ingredientBox : ingredientBoxes) {
            if (ingredientBox.getIngredient().getId() == ingredientId && ingredientBox.getAmount() > 0) {
                ingredientBox.reduceAmount(1);
                return Optional.of(ingredientBox.getIngredient());
            }
        }
        return Optional.empty();
    }

    public void addIngredient(Ingredient ingredient) {
        for (IngredientBox ingredientBox : ingredientBoxes) {
            if (ingredientBox.getIngredient().getId() == ingredient.getId()) {
                ingredientBox.addAmount(1);
                return;
            }
        }
        ingredientBoxes.add(new IngredientBox(ingredient, 1));
    }
}
