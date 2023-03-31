package learn.recipes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appUserId;
    @NonNull
    @NotBlank
    private String username;
    @NonNull
    @NotBlank
    @Column(name = "password_hash")
    private String password;

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


    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "app_user_role",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "app_role_id")
    )
    private Set<AppRole> roles = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "recipebook",
                joinColumns = @JoinColumn(name = "app_user_id"),
                inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private Set<Recipe> recipes = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    public void addAuthorities(Collection<String> authorityNames) {
        roles.clear();
        for (String name : authorityNames) {
            AppRole role = new AppRole();
            role.setRoleName(name);
            roles.add(role);
        }
    }
}
