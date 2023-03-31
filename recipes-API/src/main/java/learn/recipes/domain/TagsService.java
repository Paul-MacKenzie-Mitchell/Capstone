package learn.recipes.domain;

import learn.recipes.data.TagsRepository;
import learn.recipes.models.Food;
import learn.recipes.models.Tags;
import learn.recipes.validation.Result;
import learn.recipes.validation.ResultType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsService {

    private final TagsRepository tagsRepository;

    public TagsService(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    public List<Tags> findAll() {return tagsRepository.findAll(); }

    public Tags findById(int tagsId) {return tagsRepository.findById(tagsId).orElse(null);}

    public Result<Tags> save(Tags tag) {
        Result<Tags> result = validate(tag);
        if(!result.isSuccess()) {
            return result;
        }

        Tags payload = tagsRepository.save(tag);
        result.setPayload(payload);
        return result;

    }

    public boolean deleteById(int tagId) {

        if (tagId <= 0) {
            return false;
        }

        if (tagsRepository.findById(tagId).isEmpty()) {
            return false;
        }

        tagsRepository.deleteById(tagId);
        return true;
    }

    private Result<Tags> validate(Tags tag) {
        Result<Tags> result = new Result<>();

        // TODO: do we want to change some of these not founds into INVALID types? or is there a reason for all of them being NOT_FOUNDs
        if (tag == null) {
            result.addErr("", "tag cannot be null", ResultType.NOT_FOUND);
            return result;
        }

        if (tag.getTagId() > 0) {
            if (!tagsRepository.existsById(tag.getTagId())) {
                result.addErr("", "not found", ResultType.NOT_FOUND);
                return result;
            }
        }

        if(Validations.isNullOrBlank(tag.getTagName())) {
            result.addErr("", "tag name is required", ResultType.NOT_FOUND);
        }

        // TODO: either add findByTagName method in repository, or remove unique requirement in database/SQL
//        if(tagsRepository.findByTagName(tag.getTagName())) {
//            result.addErr("", "tag name must be unique", ResultType.INVALID);
//        }

        if(Validations.isNullOrBlank(tag.getDefaultImage())) {
            result.addErr("", "an image url is required", ResultType.NOT_FOUND);
        }
        return result;
    }

}
