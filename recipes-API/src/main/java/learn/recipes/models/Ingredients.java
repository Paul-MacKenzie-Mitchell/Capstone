package learn.recipes.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Ingredients {
    @NonNull
    private int recipeId;
    @NonNull
    private int foodId;
    @NonNull
    private double amount;

    private String measurementUnit;
}
