package learn.recipes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.URL;

@Entity
@Data
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagId;
    @NonNull
    @NotBlank
    private String tagName;
    @NotBlank
    @URL
    private String defaultImage;
}
