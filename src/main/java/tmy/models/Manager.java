package tmy.models;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class Manager {
    private final Logger logger = Logger.getLogger(Manager.class.getName());
    private final List<Chief> chiefs = new ArrayList<>();

    public void addChief(Chief chief) {
        chiefs.add(chief);
    }

    /**
     * Assigns all the dishes from an order to the chiefs. If the free chief is found, creates a new thread in which the
     * chief cooks the dish. If there are no available chiefs, waits until the chief have been found.
     * @param order dishes to cook.
     * @throws InterruptedException if the thread have been interrupted.
     */
    public void assignOrder(Order order) throws InterruptedException {
        List<Dish> requestedDishes = order.getDishes();

        for (Dish dish : requestedDishes) {
            while (true) {
                Chief chief = getFreeChief();

                if (chief != null) {
                    logger.info("Order for " + order.getCustomer().getName() + " is assigned to the chief #"
                            + chief.getChiefId());

                    chief.setCooking(true);
                    new Thread(() -> {
                        try {
                            chief.cook(dish);
                        } catch (IllegalStateException | InterruptedException e) {
                            logger.severe(e.getMessage());
                        }
                    }).start();
                    break;
                }

                Thread.sleep(100);
            }
        }
    }

    /**
     * Gets the first available chief.
     * @return the available chief.
     */
    private Chief getFreeChief() {
        return chiefs.stream()
                .filter(chief -> !chief.isCooking())
                .findFirst()
                .orElse(null);
    }
}
