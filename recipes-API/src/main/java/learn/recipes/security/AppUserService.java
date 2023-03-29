package learn.recipes.security;

import learn.recipes.data.AppUserRepository;
import learn.recipes.domain.Validations;
import learn.recipes.models.AppUser;
import learn.recipes.validation.Result;
import learn.recipes.validation.ResultType;
import org.aspectj.weaver.loadtime.Agent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> findAll() {return appUserRepository.findAll(); }

    public AppUser findById(int appUserId) {return appUserRepository.findById(appUserId).orElse(null);}

    public Result<AppUser> save(AppUser user) {
        Result<AppUser> result = validate(user);
        if(!result.isSuccess()) {
            return result;
        }

        AppUser payload = appUserRepository.save(user);
        result.setPayload(payload);
        return result;

    }


    public boolean deleteById(int appUserId) {
        if (appUserId <= 0) {
            return false;
        }
        if (appUserRepository.findById(appUserId).isEmpty()) {
            return false;
        }

        appUserRepository.deleteById(appUserId);
        return true;
    }

    private Result<AppUser> validate(AppUser user) {
        Result<AppUser> result = new Result<>();

        if (user == null) {
            result.addErr("", "User cannot be null", ResultType.NOT_FOUND);
            return result;
        }

        if (user.getAppUserId() > 0) {
            if (!appUserRepository.existsById(user.getAppUserId())) {
                result.addErr("", "not found", ResultType.NOT_FOUND);
                return result;
            }
        }
        if(Validations.isNullOrBlank(user.getUserName())) {
            result.addErr("", "user name is required", ResultType.NOT_FOUND);
        }
        if(Validations.isNullOrBlank(user.getPasswordHash())) {
            result.addErr("", "password is required", ResultType.NOT_FOUND);
        }
        if(!user.isEnabled() && user.isEnabled()) {
            result.addErr("", "user must be enabled or not enabled", ResultType.INVALID);
        }
        if(Validations.isNullOrBlank(user.getFirstName())) {
            result.addErr("", "first name is required", ResultType.NOT_FOUND);
        }
        if(Validations.isNullOrBlank(user.getLastName())) {
            result.addErr("", "last name is required", ResultType.NOT_FOUND);
        }
        if(Validations.isNullOrBlank(user.getEmail())) {
            result.addErr("", "email is required", ResultType.NOT_FOUND);
        }
        if(user.getDob().isAfter(LocalDate.now())) {
            result.addErr("", "date of birth cannot be in the future", ResultType.INVALID);
        }

        return result;
    }

}