package learn.recipes.data;

import jakarta.transaction.Transactional;
import learn.recipes.App;
import learn.recipes.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AppUserRepositoryTest {

    @Autowired
    AppUserRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }



    @Test
    @Transactional
    void shouldFind3Users() {
        List<AppUser> users = repository.findAll();
        assertEquals(3, users.size());
    }
}