package learn.recipes;

import learn.recipes.models.AppUser;

import java.time.LocalDate;

public class TestHelper {

    static public AppUser makeAppUser(int id) {
        AppUser user = new AppUser();
        user.setAppUserId(id);
        user.setUserName("TestName");
        user.setPasswordHash("Password");
        user.setEnabled(true);
        user.setFirstName("First");
        user.setLastName("Last");
        user.setEmail("TestEmail@email.com");
        user.setDob(LocalDate.of(1981, 10, 02));
        return user;
    }

}
//    private int appUserId;
//
//    private String userName;
//
//    private String passwordHash;
//
//    private boolean enabled;
//
//    private String firstName;
//
//    private String lastName;
//
//    private String email;
//
//    private LocalDate dob;