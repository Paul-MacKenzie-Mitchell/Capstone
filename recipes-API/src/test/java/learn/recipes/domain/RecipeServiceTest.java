package learn.recipes.domain;

import learn.recipes.data.KnownGoodState;
import learn.recipes.data.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RecipeServiceTest {

    @Autowired
    RecipeService service;

    @MockBean
    RecipeRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

}
