package learn.recipes.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Tags {

    private int tagId;

    private String tagName;

    private String defaultImage;
}
