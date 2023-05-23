package learn.recipes.data;

import learn.recipes.models.Recipebook;
import org.springframework.dao.DataAccessException;

public interface RecipebookRepository {

    boolean deleteById(int userId, int recipeId) throws DataAccessException;

    Recipebook add(Recipebook recipeEntry) throws DataAccessException;
}
