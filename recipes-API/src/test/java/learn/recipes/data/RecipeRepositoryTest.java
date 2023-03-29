package learn.recipes.data;

import jakarta.transaction.Transactional;
import learn.recipes.models.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RecipeRepositoryTest {
    @Autowired
    RecipeRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    @Transactional
    void shouldFind2To4Recipes() {
        List<Recipe> recipes =  repository.findAll();
        assertTrue(recipes.size() >= 2 || recipes.size() <= 4);
    }
}