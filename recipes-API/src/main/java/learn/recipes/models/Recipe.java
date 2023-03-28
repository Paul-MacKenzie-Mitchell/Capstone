package learn.recipes.models;


import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Recipe {

    private int recipeId;

    private String title;

    private String instructions;

    private String recipeDescription;

    private int cookTime;

    private int prepTime;

    private int calories;

    private int servings;

    private String imageUrl;
}
