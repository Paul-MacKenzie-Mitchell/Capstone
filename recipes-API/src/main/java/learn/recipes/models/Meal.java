package learn.recipes.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalTime;

@Entity
@Data
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mealId;
    @Temporal(TemporalType.TIME)
    private LocalTime time;
    @NonNull
    @NotBlank
    private String mealCategory;
}
