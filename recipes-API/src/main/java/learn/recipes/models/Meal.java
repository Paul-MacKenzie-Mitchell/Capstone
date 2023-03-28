package learn.recipes.models;


import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
public class Meal {

    private int mealId;

    private LocalTime time;

    private String mealCategory;
}
