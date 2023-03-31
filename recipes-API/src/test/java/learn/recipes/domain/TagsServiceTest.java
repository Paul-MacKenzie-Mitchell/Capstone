package learn.recipes.domain;

import learn.recipes.TestHelper;
import learn.recipes.data.TagsRepository;
import learn.recipes.models.Tags;
import learn.recipes.validation.Result;
import learn.recipes.validation.ResultType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TagsServiceTest {

    @Autowired
    TagsService service;

    @MockBean
    TagsRepository repository;


    @Test
    void shouldAddTag() {
        Tags validNewTag = TestHelper.makeTag(0);

        when(repository.save(validNewTag)).thenReturn(validNewTag);
        Result<Tags> result = service.save(validNewTag);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldUpdateTag() {
        Tags validUpdateTag = TestHelper.makeTag(1);

        when(repository.existsById(validUpdateTag.getTagId())).thenReturn(true);
        when(repository.save(validUpdateTag)).thenReturn(validUpdateTag);
        Result<Tags> result = service.save(validUpdateTag);

        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldDeleteTag() {
        Tags tag = TestHelper.makeTag(2);
        when(repository.findById(tag.getTagId())).thenReturn(Optional.of(tag));

        assertTrue(service.deleteById(2));
    }

    @Test
    void shouldNotDeleteTagWithInvalidId() {
        Tags invalidIdTag = TestHelper.makeTag(0);

        assertFalse(service.deleteById(invalidIdTag.getTagId()));
    }

    @Test
    void shouldNotDeleteMissingTag() {
        Tags missingTag = TestHelper.makeTag(7);
        when(repository.findById(missingTag.getTagId())).thenReturn(Optional.empty());

        assertFalse(service.deleteById(missingTag.getTagId()));
    }

    @Test
    void shouldNotSaveNullTag() {
        Result<Tags> result = service.save(null);

        assertFalse(result.isSuccess());
        assertEquals("tag cannot be null", result.getErrs().get(0).getMessage());
    }

    @Test
    void shouldNotUpdateMissingTag() {
        Tags missingTag = TestHelper.makeTag(7);
        when(repository.existsById(missingTag.getTagId())).thenReturn(false);

        Result<Tags> result = service.save(missingTag);

        assertFalse(result.isSuccess());
        assertEquals("not found", result.getErrs().get(0).getMessage());
    }

    @Test
    void shouldNotSaveTagWithBlankOrNullName() {
        Tags blankNameTag = TestHelper.makeTag(0);
        blankNameTag.setTagName(" ");

        Result<Tags> blankResult = service.save(blankNameTag);

        assertFalse(blankResult.isSuccess());
        assertEquals("tag name is required", blankResult.getErrs().get(0).getMessage());

        Tags nullNameTag = TestHelper.makeTag(0);
        nullNameTag.setTagName(null);

        Result<Tags> nullResult = service.save(nullNameTag);

        assertFalse(nullResult.isSuccess());
        assertEquals("tag name is required", nullResult.getErrs().get(0).getMessage());
    }

    @Test
    void shouldNotSaveTagWithNullDefaultImage() {
        Tags nullDefaultImageTag = TestHelper.makeTag(0);
        nullDefaultImageTag.setDefaultImage(null);

        Result<Tags> result = service.save(nullDefaultImageTag);

        assertFalse(result.isSuccess());
        assertEquals("an image url is required", result.getErrs().get(0).getMessage());
    }

    // TODO: make tagName unique in SQL, add findByTagName method to repository, test in repository, add validation for unique tag name in service

}
