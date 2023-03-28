package tmy.models;

import java.time.Duration;
import java.util.List;

public final class Order {
    private final List<Dish> dishes;
    private final Customer customer;

    public Order(List<Dish> dishes, Customer customer) {
        this.dishes = dishes;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Dish> getDishes() {
        return List.copyOf(dishes);
    }

    /**
     * Sums all the dish cooking duration at the order and gets the overall duration to cook all the dishes in the order.
     * @return duration of order cooking.
     */
    public Duration getDuration() {
        return dishes.stream()
                .map(Dish::getDuration)
                .reduce(Duration.ZERO, Duration::plus);
    }
}
