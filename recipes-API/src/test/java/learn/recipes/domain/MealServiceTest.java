package learn.recipes.domain;

import learn.recipes.TestHelper;
import learn.recipes.data.MealRepository;
import learn.recipes.models.Meal;
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
public class MealServiceTest {

    @Autowired
    MealService service;

    @MockBean
    MealRepository repository;

    @Test
    void shouldAddMeal() {
        Meal validNewMeal = TestHelper.makeMeal(0);

        when(repository.save(validNewMeal)).thenReturn(validNewMeal);
        Result<Meal> result = service.save(validNewMeal);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldUpdateMeal() {
        Meal validUpdateMeal = TestHelper.makeMeal(1);

        when(repository.existsById(1)).thenReturn(true);
        when(repository.save(validUpdateMeal)).thenReturn(validUpdateMeal);
        Result<Meal> result = service.save(validUpdateMeal);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldDeleteMeal() {
        Meal meal = TestHelper.makeMeal(2);
        when(repository.findById(2)).thenReturn(Optional.of(meal));
        assertTrue(service.deleteById(2));
    }

    @Test
    void shouldNotDeleteMealWithInvalidId() {
        Meal invalidIdMeal = TestHelper.makeMeal(0);

        assertFalse(service.deleteById(invalidIdMeal.getMealId()));
    }

    @Test
    void shouldNotDeleteMissingMeal() {
        Meal missingMeal = TestHelper.makeMeal(7);
        when(repository.findById(7)).thenReturn(Optional.empty());

        assertFalse(service.deleteById(missingMeal.getMealId()));
    }

    // TODO: add blank/null meal category validation if we decide we want it to be required (do we want it to be required?)
    // if so, then make sure to uncomment validation in MealService class
    @Test
    void shouldNotSaveNullMeal() {
        Result<Meal> result = service.save(null);

        assertFalse(result.isSuccess());
        assertEquals("meal cannot be null", result.getErrs().get(0).getMessage());
    }

    @Test
    void shouldNotUpdateMissingMeal() {
        Meal missingMeal = TestHelper.makeMeal(7);
        when(repository.existsById(7)).thenReturn(false);

        Result<Meal> result = service.save(missingMeal);

        assertFalse(result.isSuccess());
        assertEquals("not found", result.getErrs().get(0).getMessage());
    }

    // TODO ... which equal do I use??
    @Test
    void shouldNotSaveMealWithNoTimestamp() {
        Meal noTimestampMeal = TestHelper.makeMeal(0);
        noTimestampMeal.setTime(null);

        Result<Meal> result = service.save(noTimestampMeal);

        assertFalse(result.isSuccess());
        assertEquals("meal must have a timestamp in hh:mm:ss form", result.getErrs().get(0).getMessage());
    }

}
