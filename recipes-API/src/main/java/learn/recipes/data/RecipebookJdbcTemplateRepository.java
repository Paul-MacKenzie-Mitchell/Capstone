package learn.recipes.data;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RecipebookJdbcTemplateRepository implements RecipebookRepository {

    private final JdbcTemplate jdbcTemplate;

    public RecipebookJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean deleteById(int userId, int recipeId) throws DataAccessException {
        final String sql = "delete from recipebook where app_user_id = ? AND recipe_id = ?;";
        return jdbcTemplate.update(sql, userId, recipeId) > 0;
    }

}
