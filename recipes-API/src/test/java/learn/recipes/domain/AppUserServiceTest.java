package learn.recipes.domain;

import learn.recipes.TestHelper;
import learn.recipes.data.KnownGoodState;
import learn.recipes.data.AppUserRepository;
import learn.recipes.models.AppUser;
import learn.recipes.security.AppUserService;
import learn.recipes.validation.Result;
import learn.recipes.validation.ResultType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AppUserServiceTest {

    @Autowired
    AppUserService service;

    @MockBean
    AppUserRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void shouldAddAppUser() {
        AppUser validNewUser = TestHelper.makeAppUser(0);

        when(repository.save(validNewUser)).thenReturn(validNewUser);
        Result<AppUser> result = service.save(validNewUser);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }
}
