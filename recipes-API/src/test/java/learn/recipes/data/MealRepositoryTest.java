package learn.recipes.data;

import jakarta.transaction.Transactional;
import learn.recipes.TestHelper;
import learn.recipes.models.Meal;
import learn.recipes.models.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
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

    @Test
    @Transactional
    void shouldAddMeal() {
        Meal newMeal = TestHelper.makeMeal(0);
        Meal actual = repository.save(newMeal);
        assertEquals(4, actual.getMealId());

        Meal expectedNewMeal = TestHelper.makeMeal(4);
        actual = repository.findById(4).orElse(null);
        assertEquals(expectedNewMeal, actual);
    }

    @Test
    @Transactional
    void shouldDeleteMeal() {
        repository.deleteById(2);
        assertNull(repository.findById(2).orElse(null));
    }

    @Test
    @Transactional
    void shouldUpdateExistingMeal() {
        Meal meal = repository.findById(1).orElse(null);
        meal.setTime(LocalTime.of(12, 00, 00));
        meal.setMealCategory("Test Meal Category Update");
        repository.save(meal);

        Meal updatedMeal = repository.findById(1).orElse(null);
        LocalTime time = LocalTime.of(12, 00, 00);
        assertEquals(time, updatedMeal.getTime());
        assertEquals("Test Meal Category Update", updatedMeal.getMealCategory());
    }
}