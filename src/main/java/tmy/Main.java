package tmy;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import tmy.models.Chief;
import tmy.models.ChiefAmount;
import tmy.models.Customer;
import tmy.models.Dish;
import tmy.models.IngredientBox;
import tmy.models.IngredientStorage;
import tmy.models.Manager;
import tmy.models.Menu;
import tmy.models.Restaurant;
import tmy.utils.JsonFileReader;
import tmy.utils.LoggerUtility;

public class Main {
    private static final ClassLoader classLoader = Main.class.getClassLoader();

    public static void main(String[] args) {
        Restaurant restaurant;
        try {
            restaurant = createRestaurant();
        } catch (IOException e) {
            LoggerUtility.severe(e.getMessage());
            return;
        }
        restaurant.run();
    }

    /**
     * Reads the info from .json files and creates the restaurant instance.
     * 
     * @return restaurant.
     * @throws IOException if there was a problem while reading the file.
     */
    private static Restaurant createRestaurant() throws IOException {
        List<Customer> customers = JsonFileReader.getCustomers(getResourcePath("customers.json"));
        List<Dish> dishes = JsonFileReader.getDishes(getResourcePath("dishes.json"));
        List<IngredientBox> ingredientBoxes = JsonFileReader.getIngredientBoxes(getResourcePath("storage.json"));
        ChiefAmount amountOfChiefs = JsonFileReader.getAmountOfChiefs(getResourcePath("chiefs.json"));
        Manager manager = createManager(ingredientBoxes, amountOfChiefs.getAmount());

        return new Restaurant(customers, new Menu(dishes), manager);
    }

    /**
     * Creates a manager and chiefs under his command.
     * 
     * @param ingredientBoxes boxes to get ingredients from.
     * @param amountOfChiefs  amount of chiefs at the restaurant.
     * @return manager.
     */
    private static Manager createManager(List<IngredientBox> ingredientBoxes, int amountOfChiefs) {
        IngredientStorage storage = new IngredientStorage(ingredientBoxes);
        Manager manager = new Manager();
        for (int i = 0; i < amountOfChiefs; i++) {
            manager.addChief(new Chief(i + 1, storage));
        }
        return manager;
    }

    private static InputStream getResourcePath(String path) {
        return classLoader.getResourceAsStream(path);
    }
}