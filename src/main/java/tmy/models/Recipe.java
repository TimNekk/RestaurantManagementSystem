package tmy.models;

import java.time.Duration;
import java.util.List;

public final class Recipe {
    private int id;
    private List<Operation> operations;

    public int getId() {
        return id;
    }

    /**
     * Sums all the operations duration and gets the overall duration to cook the dish.
     * @return duration of dish cooking.
     */
    public Duration getCookingTime() {
        return operations.stream()
                .map(Operation::getDuration)
                .reduce(Duration.ZERO, Duration::plus);
    }

    public List<Operation> getOperations() {
        return List.copyOf(operations);
    }
}
