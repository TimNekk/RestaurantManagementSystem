package tmy.models;

import java.util.ArrayList;
import java.util.List;

import tmy.utils.LoggerUtility;

public final class Manager {
    private final List<Chief> chiefs = new ArrayList<>();

    public void addChief(Chief chief) {
        chiefs.add(chief);
    }

    /**
     * Assigns all the dishes from an order to the chiefs. If the free chief is
     * found, creates a new thread in which the
     * chief cooks the dish. If there are no available chiefs, waits until the chief
     * have been found.
     * 
     * @param order dishes to cook.
     * @throws InterruptedException if the thread have been interrupted.
     */
    public void assignOrder(Order order) throws InterruptedException {
        List<Dish> requestedDishes = order.getDishes();

        for (Dish dish : requestedDishes) {
            while (true) {
                Chief chief = getFreeChief();

                if (chief == null) {
                    Thread.sleep(100);
                    continue;
                }

                LoggerUtility.info("Order for {0} is assigned to the chief #{1}", order.getCustomer().getName(),
                        chief.getChiefId());

                chief.setCooking(true);
                new Thread(() -> {
                    try {
                        chief.cook(dish);
                    } catch (InterruptedException e) {
                        LoggerUtility.severe(e.getMessage());
                        Thread.currentThread().interrupt();
                    } catch (IllegalStateException e) {
                        LoggerUtility.warning(e.getMessage());
                    }
                }).start();
                break;
            }
        }
    }

    /**
     * Gets the first available chief.
     * 
     * @return the available chief.
     */
    private Chief getFreeChief() {
        return chiefs.stream()
                .filter(chief -> !chief.isCooking())
                .findFirst()
                .orElse(null);
    }
}
