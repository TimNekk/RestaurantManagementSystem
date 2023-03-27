package tmy.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public final class Chief {
    private final Logger logger = Logger.getLogger(Chief.class.getName());
    private final int id;
    private final IngredientStorage storage;
    private boolean isCooking = false;

    public Chief(int id, IngredientStorage ingredientStorage) {
        this.id = id;
        this.storage = ingredientStorage;
    }

    public int getChiefId() {
        return id;
    }

    public synchronized boolean isCooking() {
        return isCooking;
    }

    public synchronized void setCooking(boolean cooking) {
        isCooking = cooking;
    }

    public void cook(Dish dish) throws IllegalStateException, InterruptedException {
        logger.info("Chief #" + id + " started cooking " + dish.getName());

        for (Operation operation : dish.getRecipe().getOperations()) {
            List<Ingredient> ingredients = new ArrayList<>(operation.getIngredientIds().size());

            // Getting ingredients from storage
            for (Integer ingredientId : operation.getIngredientIds()) {
                Optional<Ingredient> optionalIngredient = storage.getIngredient(ingredientId);

                if (optionalIngredient.isPresent()) {
                    ingredients.add(optionalIngredient.get());
                } else {
                    ingredients.forEach(storage::addIngredient);
                    throw new IllegalStateException("Ingredient with id " + ingredientId + " is not found");
                }
            }

            String ingredientsString = ingredients.stream()
                    .map(Ingredient::getName)
                    .reduce((s1, s2) -> s1 + ", " + s2)
                    .orElse("");

            logger.info(
                    "Chief #" + id + " is " + operation.getType().toString().toLowerCase() + " " + ingredientsString);
            Thread.sleep(operation.getDuration().toMillis());
        }

        setCooking(false);
        logger.info("Chief #" + id + " finished cooking " + dish.getName());
    }
}
