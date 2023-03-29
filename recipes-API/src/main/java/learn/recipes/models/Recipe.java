package learn.recipes.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.URL;

@Entity
@Data
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;
    @NonNull
    @NotBlank
    private String title;
    @NonNull
    @NotBlank
    private String instructions;
    @NonNull
    @NotBlank
    private String recipeDescription;
    @Min(value = 0)
    private int cookTime;
    @Min(value = 1)
    private int prepTime;
    @Min(value = 1)
    private int calories;
    @Min(value = 1)
    private int servings;
    @URL
    private String imageUrl;
}
