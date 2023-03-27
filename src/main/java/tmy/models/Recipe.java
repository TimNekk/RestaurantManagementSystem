package tmy.models;

import java.time.Duration;
import java.util.List;

public final class Recipe {
    private int id;
    private List<Operation> operations;

    public int getId() {
        return id;
    }

    public Duration getCookingTime() {
        return operations.stream()
                .map(Operation::getDuration)
                .reduce(Duration.ZERO, Duration::plus);
    }

    public List<Operation> getOperations() {
        return List.copyOf(operations);
    }
}
