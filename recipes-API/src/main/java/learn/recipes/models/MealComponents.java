package learn.recipes.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NonNull;


@Data
public class MealComponents {

    private int recipeId;

    private int foodId;
    @NonNull
    private int mealId;
    @NonNull
    private double amount;

    private String measurementUnit;
}
