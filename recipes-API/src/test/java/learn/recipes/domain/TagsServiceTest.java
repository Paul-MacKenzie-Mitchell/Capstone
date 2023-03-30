package learn.recipes.domain;

import learn.recipes.data.KnownGoodState;
import learn.recipes.data.TagsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

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
    void shouldAddValidSecurityClearance() {
        SecurityClearance valid = new SecurityClearance(0, "Test Service Add");

        when(repository.add(valid)).thenReturn(valid);
        Result<SecurityClearance> result = service.add(valid);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }



}
