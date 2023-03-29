package learn.recipes.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.URL;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "recipe_tags",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tags> tags = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "ingredients",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private Set<Food> foods = new HashSet<>();


}
