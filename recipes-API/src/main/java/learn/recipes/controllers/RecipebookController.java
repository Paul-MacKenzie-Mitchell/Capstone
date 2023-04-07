package learn.recipes.controllers;

import learn.recipes.domain.RecipebookService;
import learn.recipes.validation.Result;
import learn.recipes.validation.ResultType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/recipebook")
public class RecipebookController {

    private final RecipebookService service;

    public RecipebookController(RecipebookService service) {
        this.service = service;
    }


    @DeleteMapping("/{userId}/{recipeId}")
    public ResponseEntity<Void> deleteById(@PathVariable int userId, @PathVariable int recipeId) {
        Result result = service.deleteById(userId, recipeId);

        if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        if(result.getType() == ResultType.INVALID) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }

}
