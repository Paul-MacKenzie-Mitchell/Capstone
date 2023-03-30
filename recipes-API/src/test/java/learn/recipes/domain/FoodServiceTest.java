package learn.recipes.domain;

import learn.recipes.TestHelper;
import learn.recipes.data.KnownGoodState;
import learn.recipes.data.FoodRepository;
import learn.recipes.models.Food;
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
public class FoodServiceTest {

    @Autowired
    FoodService service;

    @MockBean
    FoodRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void shouldAddFood() {
        Food validNewFood = TestHelper.makeFood(0);

        when(repository.save(validNewFood)).thenReturn(validNewFood);
        Result<Food> result = service.save(validNewFood);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }
}
