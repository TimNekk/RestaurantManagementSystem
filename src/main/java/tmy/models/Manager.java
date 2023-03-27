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

    private Chief getFreeChief() {
        return chiefs.stream()
                .filter(chief -> !chief.isCooking())
                .findFirst()
                .orElse(null);
    }
}
