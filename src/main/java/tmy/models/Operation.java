package tmy.models;

import java.time.Duration;
import java.util.List;

public final class Operation {
    private OperationType type;
    private Duration duration;
    private List<Integer> ingredientIds;

    public OperationType getType() {
        return type;
    }

    public Duration getDuration() {
        return duration;
    }

    public List<Integer> getIngredientIds() {
        return List.copyOf(ingredientIds);
    }
}
