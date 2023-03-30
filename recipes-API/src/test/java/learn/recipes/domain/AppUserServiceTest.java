package learn.recipes.domain;

import learn.recipes.data.KnownGoodState;
import learn.recipes.data.AppUserRepository;
import learn.recipes.security.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
}
