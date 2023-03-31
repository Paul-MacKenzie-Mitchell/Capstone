package learn.recipes.data;

import jakarta.transaction.Transactional;
import learn.recipes.TestHelper;
import learn.recipes.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
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


    // something weird is happening here where if I run this test individually, it passes, but when I try to run the entire test class, it fails
    @Test
    @Transactional
    void shouldFind2To4Users() {
        List<AppUser> allUsers = repository.findAll();
        assertNotNull(allUsers);
        assertTrue(allUsers.size() >= 2 && allUsers.size() <= 4);
    }

    @Test
    @Transactional
    void shouldFindUserById() {
        AppUser user = repository.findById(3).orElse(null);
        assertNotNull(user);
        assertEquals("admin@admin.com", user.getEmail());
        assertEquals(LocalDate.of(2000, 01, 01), user.getDob());
    }

    @Test
    @Transactional
    void shouldFindUserByUsername() {
        AppUser user = repository.findByUsername("adminuser");
        assertNotNull(user);
        assertEquals(3, user.getAppUserId());
        assertEquals("adminuserlast", user.getLastName());
    }

    // TODO when we add an AppUser, we should add roles for them as well (see addAuthorities method example in board-game-api)
    @Test
    @Transactional
    void shouldAddAppUser() {
        AppUser newUser = TestHelper.makeAppUser(0);
        AppUser actual = repository.save(newUser);
        assertEquals(4, actual.getAppUserId());

        AppUser expectedNewUser = TestHelper.makeAppUser(4);
        actual = repository.findById(4).orElse(null);
        assertEquals(expectedNewUser, actual);
    }

    @Test
    @Transactional
    void shouldDeleteAppUser() {
        repository.deleteById(2);
        assertNull(repository.findById(2).orElse(null));
        assertNull(repository.findByUsername("admin"));
    }

    // same problem as findAll method: passes individually, but not with the entire class (returns null user variable for line 93)
    @Test
    @Transactional
    void shouldUpdateExistingAppUser() {
        AppUser user = repository.findById(1).orElse(null);
        user.setFirstName("First Name Update");
        user.setUsername("Username Update");
        repository.save(user);

        AppUser updatedUser = repository.findById(1).orElse(null);
        assertEquals("First Name Update", updatedUser.getFirstName());
        assertEquals("Username Update", updatedUser.getUsername());
    }
}