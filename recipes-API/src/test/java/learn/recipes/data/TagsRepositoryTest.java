package learn.recipes.data;

import jakarta.transaction.Transactional;
import learn.recipes.TestHelper;
import learn.recipes.models.Tags;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class TagsRepositoryTest {

    @Autowired
    TagsRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    @Transactional
    void shouldFind2To4Tags() {
        List<Tags> allTags = repository.findAll();
        assertNotNull(allTags);
        assertTrue(allTags.size() >= 2 && allTags.size() <= 4);
    }

    @Test
    @Transactional
    void shouldAddTag() {
        Tags newTag = TestHelper.makeTag(0);
        Tags actual = repository.save(newTag);
        assertEquals(4, actual.getTagId());

        Tags expectedNewTag = TestHelper.makeTag(4);
        actual = repository.findById(4).orElse(null);
        assertEquals(expectedNewTag, actual);
    }

    @Test
    @Transactional
    void shouldDeleteTag() {
        repository.deleteById(2);
        assertNull(repository.findById(2).orElse(null));
    }

    @Test
    @Transactional
    void shouldUpdateExistingTag() {
        Tags tag = repository.findById(1).orElse(null);
        tag.setTagName("Test Name Update");
        tag.setDefaultImage("https://default-image-test-update.jpg");
        repository.save(tag);

        Tags updatedTag = repository.findById(1).orElse(null);
        assertEquals("Test Name Update", updatedTag.getTagName());
        assertEquals("https://default-image-test-update.jpg", updatedTag.getDefaultImage());
    }

}