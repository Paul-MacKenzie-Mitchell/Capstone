package learn.recipes.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mealId;
    @Temporal(TemporalType.TIME)
    private LocalTime time;
    @NonNull
    @NotBlank
    private String mealCategory;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "meal_components",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")

    )
    private Set<Recipe> recipes = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "meal_components",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")

    )
    private Set<Food> food = new HashSet<>();
}
