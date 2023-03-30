package learn.recipes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appRoleId;

    @NonNull
    @NotBlank
//    @Column(unique=true)
    private String roleName;


}

