package learn.recipes.domain;

import learn.recipes.TestHelper;
import learn.recipes.data.RecipeRepository;
import learn.recipes.models.Recipe;
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
public class RecipeServiceTest {

    @Autowired
    RecipeService service;

    @MockBean
    RecipeRepository repository;

    @Test
    void shouldAddRecipe() {
        Recipe validNewRecipe = TestHelper.makeRecipe(0);

        when(repository.save(validNewRecipe)).thenReturn(validNewRecipe);
        Result<Recipe> result = service.save(validNewRecipe);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldUpdateRecipe() {
        Recipe validUpdateRecipe = TestHelper.makeRecipe(1);

        when(repository.existsById(1)).thenReturn(true);
        when(repository.save(validUpdateRecipe)).thenReturn(validUpdateRecipe);
        Result<Recipe> result = service.save(validUpdateRecipe);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldDeleteRecipe() {
        Recipe recipe = TestHelper.makeRecipe(2);
        when(repository.findById(2)).thenReturn(Optional.of(recipe));
        assertTrue(service.deleteById(2));
    }

    @Test
    void shouldNotDeleteRecipeWithInvalidId() {
        Recipe invalidIdRecipe = TestHelper.makeRecipe(0);

        assertFalse(service.deleteById(invalidIdRecipe.getRecipeId()));
    }

    @Test
    void shouldNotDeleteMissingRecipe() {
        Recipe missingRecipe = TestHelper.makeRecipe(7);
        when(repository.findById(7)).thenReturn(Optional.empty());

        assertFalse(service.deleteById(missingRecipe.getRecipeId()));
    }

}
