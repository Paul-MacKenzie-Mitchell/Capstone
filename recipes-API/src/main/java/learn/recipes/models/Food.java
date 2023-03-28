package learn.recipes.models;


import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Food {

    private int foodId;

    private String foodName;

    private double amount;

    private String measurementUnit;

    private String foodCategory;

    private String foodDescription;

}
