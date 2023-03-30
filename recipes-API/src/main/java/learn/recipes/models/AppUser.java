package learn.recipes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appUserId;
    @NonNull
    @NotBlank
    private String username;
    @NonNull
    @NotBlank
    private String passwordHash;

    private boolean enabled;
    @NonNull
    @NotBlank
    private String firstName;
    @NonNull
    @NotBlank
    private String lastName;
    @NonNull
    @NotBlank
    private String email;
//    @Temporal(TemporalType.DATE)
    private LocalDate dob;


@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "app_user_role",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "app_role_id")
    )
    private Set<AppRole> roles = new HashSet<>();

@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "recipebook",
                joinColumns = @JoinColumn(name = "app_user_id"),
                inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private Set<Recipe> recipes = new HashSet<>();
}
