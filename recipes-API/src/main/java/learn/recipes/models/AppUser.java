package learn.recipes.models;

import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class AppUser {
    private int appUserId;

    private String userName;

    private String passwordHash;

    private boolean enabled;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate dob;
}
