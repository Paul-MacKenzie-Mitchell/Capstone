package learn.recipes.domain;

import learn.recipes.TestHelper;
import learn.recipes.data.KnownGoodState;
import learn.recipes.data.TagsRepository;
import learn.recipes.models.Tags;
import learn.recipes.validation.Result;
import learn.recipes.validation.ResultType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TagsServiceTest {

    @Autowired
    TagsService service;

    @MockBean
    TagsRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    /* CRUD:
    Create/add
    Read/find
    Update/edit
    Delete
     */

    @Test
    void shouldAddTag() {
        Tags validNewTag = TestHelper.makeTag(0);

        when(repository.save(validNewTag)).thenReturn(validNewTag);
        Result<Tags> result = service.save(validNewTag);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    // is this even necessary??
    @Test
    void shouldUpdateTag() {
        Tags validNewTag = TestHelper.makeTag(4);

        when(repository.existsById(4)).thenReturn(true);
        when(repository.save(validNewTag)).thenReturn(validNewTag);
        Result<Tags> result = service.save(validNewTag);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }

//    @Test
//    void shouldDelete() {
//        when(repository.findById(2).orElse(null)).thenReturn(of(TestHelper.makeTag(2)));
//        assertTrue(service.deleteById(2));
//    }


}
