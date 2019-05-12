import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FoodItemTest {
    @Test
    void assertFoodItemCanBeConstructed() {
        String name = "bananas";
        String id = "unique-id";
        FoodItem fi = new FoodItem(name, id);
        assertEquals(name, fi.name);
        assertEquals(id, fi.uniqueIdentifier);
    }

    @Test
    void assertFoodItemCanAddNutrient() {
        FoodItem fi = new FoodItem("bananas", "id1");
        Nutrient nutrient = new Nutrient("potassium", "g", 24);
        fi.addNutrient(nutrient);
        assertEquals(fi.findMatchingNutrient("potassium"), nutrient);
    }
}
