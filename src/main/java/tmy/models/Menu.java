package tmy.models;

import java.util.List;

public final class Menu {
    private final List<Dish> dishes;

    public Menu(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Dish> getDishes() {
        return List.copyOf(dishes);
    }
}
