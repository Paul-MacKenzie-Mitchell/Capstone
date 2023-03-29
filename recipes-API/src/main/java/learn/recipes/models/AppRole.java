package learn.recipes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appRoleId;

    @NonNull
    @NotBlank
    @Column(unique=true)
    private String roleName;

//    @ManyToMany(mappedBy = "roles")
//    private Set<AppUser> users = new HashSet<>();
//
//    private AppUser appUser;
}

