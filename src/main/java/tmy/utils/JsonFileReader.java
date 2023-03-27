package tmy.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import tmy.models.ChiefAmount;
import tmy.models.Customer;
import tmy.models.Dish;
import tmy.models.IngredientBox;

public final class JsonFileReader {
    private static final ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

    private JsonFileReader() {
    }

    public static List<Customer> getCustomers(InputStream inputStream) throws IOException {
        return mapper.readValue(inputStream, new TypeReference<List<Customer>>() {
        });
    }

    public static List<Dish> getDishes(InputStream inputStream) throws IOException {
        return mapper.readValue(inputStream, new TypeReference<List<Dish>>() {
        });
    }

    public static List<IngredientBox> getIngredientBoxes(InputStream inputStream) throws IOException {
        return mapper.readValue(inputStream, new TypeReference<List<IngredientBox>>() {
        });
    }

    public static ChiefAmount getAmountOfChiefs(InputStream inputStream) throws IOException {
        return mapper.readValue(inputStream, ChiefAmount.class);
    }
}