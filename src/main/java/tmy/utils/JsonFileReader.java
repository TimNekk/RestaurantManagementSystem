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

    /**
     * Gets the list of customers from the stream.
     * @param inputStream the stream to read from.
     * @return list of customers.
     * @throws IOException if there was a problem while reading from input stream.
     */
    public static List<Customer> getCustomers(InputStream inputStream) throws IOException {
        return mapper.readValue(inputStream, new TypeReference<List<Customer>>() {
        });
    }

    /**
     * Gets the list of dishes from the stream.
     * @param inputStream the stream to read from.
     * @return list of dishes.
     * @throws IOException if there was a problem while reading from input stream.
     */
    public static List<Dish> getDishes(InputStream inputStream) throws IOException {
        return mapper.readValue(inputStream, new TypeReference<List<Dish>>() {
        });
    }

    /**
     * Gets the list of ingredient boxes from the stream.
     * @param inputStream the stream to read from.
     * @return list of ingredient boxes.
     * @throws IOException if there was a problem while reading from input stream.
     */
    public static List<IngredientBox> getIngredientBoxes(InputStream inputStream) throws IOException {
        return mapper.readValue(inputStream, new TypeReference<List<IngredientBox>>() {
        });
    }

    /**
     * Gets amount of chiefs in the restaurant from the stream.
     * @param inputStream the stream to read from.
     * @return amount of chiefs.
     * @throws IOException if there was a problem while reading from input stream.
     */
    public static ChiefAmount getAmountOfChiefs(InputStream inputStream) throws IOException {
        return mapper.readValue(inputStream, ChiefAmount.class);
    }
}