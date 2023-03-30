package learn.recipes.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Ingredients {
    @NonNull
    private int recipeId;
    @NonNull
    private int foodId;

    private double amount;

    private String measurementUnit;
}
