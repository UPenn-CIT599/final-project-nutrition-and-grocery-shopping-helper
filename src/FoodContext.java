/**
 * Simple value object that contains food name and unique identifier returned from Food Search API
 * Used as a param to request more detailed information from the Food Details API
 */
public class FoodContext {
    public String name;
    public String uniqueId;

    public FoodContext(String name, String uniqueId) {
        this.name = name;
        this.uniqueId = uniqueId;
    }

    @Override
    public String toString() {
        return name + " with id: " + uniqueId;
    }
}
