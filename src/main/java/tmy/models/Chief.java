package tmy.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * A model that cooks the dishes.
 */
public final class Chief {
    private final Logger logger = Logger.getLogger(Chief.class.getName());
    private final int id;
    private final IngredientStorage storage;
    private boolean isCooking = false;

    /**
     * Constructs {@code Chief} instance.
     * @param id id of the Chief.
     * @param ingredientStorage the storage that Chief gets the ingredients from.
     */
    public Chief(int id, IngredientStorage ingredientStorage) {
        this.id = id;
        this.storage = ingredientStorage;
    }

    /**
     * Gets the value of the {@code id} field.
     * @return id value.
     */
    public int getChiefId() {
        return id;
    }

    /**
     * Gets the value of the {@code isCooking} field.
     * @return {@code True} if the Chief is cooking, {@code False} otherwise.
     */
    public synchronized boolean isCooking() {
        return isCooking;
    }

    /**
     * Sets the value of {@code isCooking} field.
     * @param cooking is the Chief cooking right now.
     */
    public synchronized void setCooking(boolean cooking) {
        isCooking = cooking;
    }

    /**
     * Cooks the dish by going through the needed operations and getting the needed
     * ingredients from the storage.
     * @param dish dish to cook.
     * @throws IllegalStateException if the {@code IngredientStorage} doesn't have enough ingredients for the dish.
     * @throws InterruptedException if the thread was interrupted.
     */
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
