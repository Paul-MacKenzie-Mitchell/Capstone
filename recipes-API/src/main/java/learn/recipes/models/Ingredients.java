package learn.recipes.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Ingredients {

    @Id
    private int ingredientId;
    private int recipeId;
//    private int foodId;
    private double amount;
    private String measurementUnit;

//    @ManyToOne
//    @JoinColumn(name = "recipeId")
//    private Recipe recipe;

    @ManyToOne
    @JoinColumn (name = "foodId")
    private Food food;
}
