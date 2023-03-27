package tmy.models;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class Restaurant {
    private final Logger logger = Logger.getLogger(Restaurant.class.getName());
    private final List<Customer> customers;
    private final Menu menu;
    private final Manager manager;

    public Restaurant(List<Customer> customers, Menu menu, Manager manager) {
        this.customers = customers;
        this.menu = menu;
        this.manager = manager;
    }

    public void run() {
        List<Order> orders = getOrders();

        for (Order order : orders) {
            try {
                manager.assignOrder(order);
            } catch (InterruptedException e) {
                logger.severe(e.getMessage());
            }

        }
    }

    private List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        for (Customer customer : customers) {
            Order order = customer.chooseDishes(menu);
            orders.add(order);

            Duration duration = orders.stream().map(Order::getDuration).reduce(Duration.ZERO, Duration::plus);
            logger.info("Customer " + customer.getName() + " ordered "
                    + order.getDishes().stream().map(Dish::getName).reduce((a, b) -> a + ", " + b).orElse("")
                    + ". In worst case it will take " + duration.toSeconds() + " s.");

        }
        return orders;
    }
}
