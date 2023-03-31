package learn.recipes.domain;

import learn.recipes.TestHelper;
import learn.recipes.data.FoodRepository;
import learn.recipes.models.Food;
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
public class FoodServiceTest {

    @Autowired
    FoodService service;

    @MockBean
    FoodRepository repository;

    @Test
    void shouldAddFood() {
        Food validNewFood = TestHelper.makeFood(0);

        when(repository.save(validNewFood)).thenReturn(validNewFood);
        Result<Food> result = service.save(validNewFood);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldUpdateFood() {
        Food validUpdateFood = TestHelper.makeFood(1);

        when(repository.existsById(1)).thenReturn(true);
        when(repository.save(validUpdateFood)).thenReturn(validUpdateFood);
        Result<Food> result = service.save(validUpdateFood);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldDeleteFood() {
        Food food = TestHelper.makeFood(2);
        when(repository.findById(2)).thenReturn(Optional.of(food));
        assertTrue(service.deleteById(2));
    }

    @Test
    void shouldNotDeleteFoodWithInvalidId() {
        Food invalidIdFood = TestHelper.makeFood(0);

        assertFalse(service.deleteById(invalidIdFood.getFoodId()));
    }

    @Test
    void shouldNotDeleteMissingFood() {
        Food missingFood = TestHelper.makeFood(7);
        when(repository.findById(7)).thenReturn(Optional.empty());

        assertFalse(service.deleteById(missingFood.getFoodId()));
    }

    @Test
    void shouldNotSaveNullFood() {
        Result<Food> result = service.save(null);

        assertFalse(result.isSuccess());
        assertEquals("food cannot be null", result.getErrs().get(0).getMessage());
    }

    @Test
    void shouldNotUpdateMissingFood() {
        Food missingFood = TestHelper.makeFood(7);
        when(repository.existsById(7)).thenReturn(false);

        Result<Food> result = service.save(missingFood);

        assertFalse(result.isSuccess());
        assertEquals("not found", result.getErrs().get(0).getMessage());
    }


    // TODO: Lombok/JPA won't let me test null because it's marked as non-null in the class! Should I still test?
    @Test
    void shouldNotSaveFoodWithBlankName() {
        Food blankNameFood = TestHelper.makeFood(0);
        blankNameFood.setFoodName("  ");

        Result<Food> result = service.save(blankNameFood);

        assertFalse(result.isSuccess());
        assertEquals("food name is required", result.getErrs().get(0).getMessage());
    }

    @Test
    void shouldNotSaveFoodWithBlankCategory() {
        Food blankCategoryFood = TestHelper.makeFood(0);
        blankCategoryFood.setFoodCategory("");

        Result<Food> result = service.save(blankCategoryFood);

        assertFalse(result.isSuccess());
        assertEquals("food category is required", result.getErrs().get(0).getMessage());
    }

    @Test
    void shouldNotSaveFoodWithNullDescription() {
        Food nullDescriptionFood = TestHelper.makeFood(0);
        nullDescriptionFood.setFoodDescription(null);

        Result<Food> result = service.save(nullDescriptionFood);

        assertFalse(result.isSuccess());
        assertEquals("food description is required", result.getErrs().get(0).getMessage());
    }

}
