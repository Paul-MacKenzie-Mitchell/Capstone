package learn.recipes.data;

import learn.recipes.models.Recipebook;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class RecipebookJdbcTemplateRepository implements RecipebookRepository {

    private final JdbcTemplate jdbcTemplate;

    public RecipebookJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean findByIds(int userId, int recipeId) throws DataAccessException {
        final String sql = "select * from recipebook where app_user_id = ? AND recipe_id = ?;";
        return jdbcTemplate.update(sql, userId, recipeId) > 0;
    }

    @Override
    public boolean deleteById(int userId, int recipeId) throws DataAccessException {
        final String sql = "delete from recipebook where app_user_id = ? AND recipe_id = ?;";
        return jdbcTemplate.update(sql, userId, recipeId) > 0;
    }

    @Override
    public Recipebook add(Recipebook recipeEntry) throws DataAccessException {

        final String sql = "insert into recipebook values (?, ?);";

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, recipeEntry.getAppUserId());
            statement.setInt(2, recipeEntry.getRecipeId());
            return statement;
        });

        if (rowsAffected == 0) {
            return null;
        }

        return recipeEntry;
    }

}
