package tmy.models;

import java.util.List;
import java.util.Optional;

public final class IngredientStorage {
    private final List<IngredientBox> ingredientBoxes;

    public IngredientStorage(List<IngredientBox> ingredientBoxes) {
        this.ingredientBoxes = ingredientBoxes;
    }

    /**
     * Gets the needed ingredient from the boxes and reduces its amount. If the ingredient doesn't exist, returns nothing.
     * @param ingredientId id of the needed ingredient.
     * @return the ingredient if it is found in the boxes, nothing if it's not there.
     */
    public Optional<Ingredient> getIngredient(int ingredientId) {
        for (IngredientBox ingredientBox : ingredientBoxes) {
            if (ingredientBox.getIngredient().getId() == ingredientId && ingredientBox.getAmount() > 0) {
                ingredientBox.reduceAmount(1);
                return Optional.of(ingredientBox.getIngredient());
            }
        }
        return Optional.empty();
    }

    /**
     * Adds the ingredient to the storage. If the type of the ingredient is already there, adds its amount. If not,
     * adds new box to the list.
     * @param ingredient ingredient to add to the storage.
     */
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
