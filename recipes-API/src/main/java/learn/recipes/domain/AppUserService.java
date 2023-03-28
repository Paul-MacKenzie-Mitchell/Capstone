package learn.recipes.domain;

import learn.recipes.data.AppUserRepository;
import learn.recipes.models.AppUser;
import learn.recipes.validation.Result;
import learn.recipes.validation.ResultType;
import org.springframework.stereotype.Service;

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
        return result;
    }

}
//    private int appUserId;
//
//    private String userName;
//
//    private String passwordHash;
//
//    private boolean enabled;
//
//    private String firstName;
//
//    private String lastName;
//
//    private String email;
//
//    private LocalDate dob;