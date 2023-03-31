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
}
