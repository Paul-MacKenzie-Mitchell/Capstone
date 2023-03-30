package learn.recipes.data;

import jakarta.transaction.Transactional;
import learn.recipes.TestHelper;
import learn.recipes.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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

    // do we need this one? board-game-api doesn't test the findById method, plus we're kind of testing it by using it in all the other tests
    // plus I'm not sure how to approach it because it returns an Optional thing, and I'm unsure how to work with that
    @Test
    @Transactional
    void shouldFindUserById() {
        Optional<AppUser> user = repository.findById(3);
        assertNotNull(user);
    }

    @Test
    @Transactional
    void shouldFindUserByLastName() {
        List<AppUser> users = repository.findByLastNameIgnoreCase("adminuserlast");
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("adminuserlast", users.get(0).getLastName());
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

        // test below works as well, but which one is preferable?
//        AppUser newUser = TestHelper.makeAppUser(4);
//        AppUser actual = repository.save(newUser);
//        assertNotNull(actual);
//        assertEquals(4, actual.getAppUserId());
//        assertEquals("TestEmail@email.com", actual.getEmail());
//        assertEquals("Last", repository.findByLastNameIgnoreCase("Last").get(0).getLastName());

    }

    @Test
    @Transactional
    void shouldDeleteAppUser() {
        repository.deleteById(2);
        assertNull(repository.findById(2).orElse(null));
        assertEquals(0, repository.findByLastNameIgnoreCase("adminlast").size());
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