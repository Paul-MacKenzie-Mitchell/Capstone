package learn.recipes.data;

import jakarta.transaction.Transactional;
import learn.recipes.TestHelper;
import learn.recipes.domain.RecipeService;
import learn.recipes.domain.TagsService;
import learn.recipes.models.Recipe;
import learn.recipes.models.Tags;
import learn.recipes.validation.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RecipeRepositoryTest {

    @Autowired
    RecipeService service;

    @MockBean
    RecipeRepository repository;

    @Test
    @Transactional
    void shouldFind2To4Recipes() {
        List<Recipe> recipes = repository.findAll();
        assertTrue(recipes.size() >= 2 && recipes.size() <= 4);
    }

    @Test
    @Transactional
    void shouldAddRecipe() {
        Recipe newRecipe = TestHelper.makeRecipe(0);
        Recipe actual = repository.save(newRecipe);
        assertEquals(4, actual.getRecipeId());

        Recipe expectedNewRecipe = TestHelper.makeRecipe(4);
        actual = repository.findById(4).orElse(null);
        assertEquals(expectedNewRecipe, actual);
    }

    @Test
    @Transactional
    void shouldDeleteRecipe() {
        repository.deleteById(2);
        assertNull(repository.findById(2).orElse(null));
    }

    @Test
    @Transactional
    void shouldUpdateExistingRecipe() {
        Recipe recipe = repository.findById(1).orElse(null);
        recipe.setTitle("Test Title Update");
        recipe.setRecipeDescription("Test Recipe Description Update");
        repository.save(recipe);

        Recipe updatedRecipe = repository.findById(1).orElse(null);
        assertEquals("Test Title Update", updatedRecipe.getTitle());
        assertEquals("Test Recipe Description Update", updatedRecipe.getRecipeDescription());
    }

    @Test
    void shouldNotSaveRecipeWithBlankName() {
        Recipe blankNameRecipe = TestHelper.makeRecipe(0);
        blankNameRecipe.setTitle("");

        Result<Recipe> result = service.save(blankNameRecipe);

        assertFalse(result.isSuccess());
        assertEquals("recipe title is required", result.getErrs().get(0).getMessage());
    }

}