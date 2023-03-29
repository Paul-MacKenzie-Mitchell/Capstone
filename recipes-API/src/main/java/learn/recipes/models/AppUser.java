package learn.recipes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @Temporal(TemporalType.DATE)
    private LocalDate dob;

//    private ArrayList<GrantedAuthority> authorities = new ArrayList<>();

//    public AppUser(String userName, List<String> authorities) {
//    }
}
