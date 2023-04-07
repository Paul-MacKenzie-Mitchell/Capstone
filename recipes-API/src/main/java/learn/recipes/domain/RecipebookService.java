package learn.recipes.domain;

import learn.recipes.data.RecipebookRepository;
import learn.recipes.validation.Result;
import learn.recipes.validation.ResultType;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RecipebookService {

    private final RecipebookRepository repository;

    public RecipebookService(RecipebookRepository repository) {
        this.repository = repository;
    }

    public Result deleteById(int userId, int recipeId) throws DataAccessException {
        Result result = new Result();

        if(userId <= 0 || recipeId <= 0) {
            result.addErr("", "Please enter a valid recipe and valid recipebook identifier to delete from.", ResultType.INVALID);
            return result;
        }

        if (!repository.deleteById(userId, recipeId)) {
            result.addErr("", "Could not find entry to delete.", ResultType.NOT_FOUND);
        }

        return result;
    }
}
