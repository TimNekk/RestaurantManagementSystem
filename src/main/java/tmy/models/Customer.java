package tmy.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Customer {
    private String name;
    private static final int MIN_AMOUNT_OF_DISHES = 1;
    private static final int MAX_AMOUNT_OF_DISHES = 3;
    private final Random random = new Random();

    /**
     * Gets the name field value.
     * @return name of the customer.
     */
    public String getName() {
        return name;
    }

    /**
     * Makes an {@code Order} for the customer, randomly choosing dishes from the menu.
     * @param menu menu to choose dishes from
     * @return customer's order.
     */
    public Order chooseDishes(Menu menu) {
        List<Dish> dishes = menu.getDishes();

        List<Dish> chosenDishes = new ArrayList<>();
        int amountOfDishes = random.nextInt(MAX_AMOUNT_OF_DISHES - MIN_AMOUNT_OF_DISHES + 1) + MIN_AMOUNT_OF_DISHES;
        for (int i = 0; i < amountOfDishes; i++) {
            chosenDishes.add(dishes.get(random.nextInt(dishes.size())));
        }

        return new Order(chosenDishes, this);
    }
}
