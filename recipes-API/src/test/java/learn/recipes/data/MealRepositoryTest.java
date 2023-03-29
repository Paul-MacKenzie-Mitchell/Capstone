package learn.recipes.data;

import jakarta.transaction.Transactional;
import learn.recipes.models.Meal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MealRepositoryTest {

    @Autowired
    MealRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    @Transactional
    void shouldFind2To4Meals() {
        List<Meal> meals = repository.findAll();
        assertTrue(meals.size() >= 2 && meals.size() <= 4);
    }
}