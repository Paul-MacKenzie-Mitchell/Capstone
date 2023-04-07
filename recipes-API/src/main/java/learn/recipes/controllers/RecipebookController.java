package learn.recipes.controllers;

import jakarta.validation.Valid;
import learn.recipes.domain.RecipebookService;
import learn.recipes.models.Recipe;
import learn.recipes.models.Recipebook;
import learn.recipes.validation.Result;
import learn.recipes.validation.ResultType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static learn.recipes.controllers.ErrMapper.mapErrs;

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

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid Recipebook recipeEntry, BindingResult bindingResult) {
        Result<Recipebook> result = service.add(recipeEntry);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(mapErrs(bindingResult), HttpStatus.BAD_REQUEST);
        }

        if (recipeEntry.getAppUserId() <= 0 || recipeEntry.getAppUserId() <= 0) {
            return new ResponseEntity<>(mapErrs("recipeId", "Oops, something happened"), HttpStatus.BAD_REQUEST);
        }

        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrs(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

}
