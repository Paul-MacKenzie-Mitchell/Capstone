package learn.recipes.data;

import org.springframework.dao.DataAccessException;

public interface RecipebookRepository {

    boolean deleteById(int userId, int recipeId) throws DataAccessException;
}
