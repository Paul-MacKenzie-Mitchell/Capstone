package learn.recipes.domain;

import learn.recipes.TestHelper;
import learn.recipes.data.AppUserRepository;
import learn.recipes.models.AppUser;
import learn.recipes.security.AppUserService;
import learn.recipes.validation.Result;
import learn.recipes.validation.ResultType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AppUserServiceTest {

    @Autowired
    AppUserService service;

    @MockBean
    AppUserRepository repository;

    // TODO: figure out AppUserService security stuff

    @Test
    void shouldAddAppUser() {
        AppUser validNewUser = TestHelper.makeAppUser(0);

        when(repository.save(validNewUser)).thenReturn(validNewUser);
        Result<AppUser> result = service.save(validNewUser);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldUpdateAppUser() {
        AppUser validUpdateUser = TestHelper.makeAppUser(1);

        when(repository.existsById(1)).thenReturn(true);
        when(repository.save(validUpdateUser)).thenReturn(validUpdateUser);
        Result<AppUser> result = service.save(validUpdateUser);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldDeleteAppUser() {
        AppUser appUser = TestHelper.makeAppUser(2);
        when(repository.findById(2)).thenReturn(Optional.of(appUser));
        assertTrue(service.deleteById(2));
    }

    @Test
    void shouldNotDeleteUserWithInvalidId() {
        AppUser invalidIdUser = TestHelper.makeAppUser(0);

        assertFalse(service.deleteById(invalidIdUser.getAppUserId()));
    }

    @Test
    void shouldNotDeleteMissingUser() {
        AppUser missingUser = TestHelper.makeAppUser(7);
        when(repository.findById(7)).thenReturn(Optional.empty());

        assertFalse(service.deleteById(missingUser.getAppUserId()));
    }

    // TODO: update email address in SQL and repository to be unique (add findByEmail method for repo)
        // add validation to the Service class as well

    @Test
    void shouldNotSaveNullUser() {
        Result<AppUser> result = service.save(null);

        assertFalse(result.isSuccess());
        assertEquals("user cannot be null", result.getErrs().get(0).getMessage());
    }

    @Test
    void shouldNotUpdateMissingUser() {
        AppUser missingUser = TestHelper.makeAppUser(7);
        when(repository.existsById(7)).thenReturn(false);

        Result<AppUser> result = service.save(missingUser);

        assertFalse(result.isSuccess());
        assertEquals("not found", result.getErrs().get(0).getMessage());
    }

    // TODO: again with @NonNull and @NonBlank exceptions for this test below
        // so I didn't run the fields marked with those annotations:
        // username, password, firstName, lastName, email

    @Test
    void shouldNotSaveUserWithNullUsername() {
        AppUser nullUsernameUser = TestHelper.makeAppUser(0);
        nullUsernameUser.setUsername(null);

        Result<AppUser> result = service.save(nullUsernameUser);

        assertFalse(result.isSuccess());
        assertEquals("username is required", result.getErrs().get(0).getMessage());
    }

    @Test
    void shouldNotSaveUserWithExistingUsername() {
        AppUser existingUsernameUser = TestHelper.makeAppUser(7);
        when(repository.existsById(7)).thenReturn(true);
        when(repository.findByUsername(existingUsernameUser.getUsername())).thenReturn(existingUsernameUser);

        Result<AppUser> result = service.save(existingUsernameUser);

        assertFalse(result.isSuccess());
        assertEquals("username already exists", result.getErrs().get(0).getMessage());
    }

    @Test
    void shouldNotSaveUserWithFutureBirthday() {
        AppUser existingUsernameUser = TestHelper.makeAppUser(7);
        existingUsernameUser.setDob(LocalDate.now().plusDays(1));
        when(repository.existsById(7)).thenReturn(true);
        when(repository.findByUsername(existingUsernameUser.getUsername())).thenReturn(null);

        Result<AppUser> result = service.save(existingUsernameUser);

        assertFalse(result.isSuccess());
        assertEquals("date of birth cannot be in the future", result.getErrs().get(0).getMessage());
    }

    // TODO: test loadUserByUsername function

}
