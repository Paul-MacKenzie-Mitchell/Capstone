package learn.recipes.domain;

import learn.recipes.TestHelper;
import learn.recipes.data.KnownGoodState;
import learn.recipes.data.MealRepository;
import learn.recipes.models.Meal;
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
public class MealServiceTest {

    @Autowired
    MealService service;

    @MockBean
    MealRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void shouldAddMeal() {
        Meal validNewMeal = TestHelper.makeMeal(0);

        when(repository.save(validNewMeal)).thenReturn(validNewMeal);
        Result<Meal> result = service.save(validNewMeal);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }
}
