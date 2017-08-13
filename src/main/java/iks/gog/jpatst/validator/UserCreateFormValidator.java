package iks.gog.jpatst.validator;

import iks.gog.jpatst.forms.UserCreateForm;
import iks.gog.jpatst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserCreateFormValidator implements Validator {


    private UserService userService;

    public UserCreateFormValidator() {
    }

    @Autowired
    public UserCreateFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCreateForm form = (UserCreateForm) target;
        validatePasswords(errors, form);
        validateName(errors, form);
    }

    private void validatePasswords(Errors errors, UserCreateForm form) {
        //(?=.*\d.*)(?=.*[!@#$%].*)(?=.*[a-z].*)(?=.*[A-Z].*)^.{8,}$
        String password = form.getPassword();
        if (!password.equals(form.getConfirmPassword())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
        if (password.trim().length() < 8){
            errors.reject("password.too_short", "The password must be at least 8 characters");
        }

        if (!password.matches(".*[0-9].*")){
            errors.reject("password.no_number", "The password must contain at least 1 number");
        }

        if (!password.matches(".*[a-z].*")){
            errors.reject("password.no_lowercase", "The password must contain at least 1 lowercase letter");
        }

        if (!password.matches(".*[A-Z].*")){
            errors.reject("password.no_uppercase", "The password must contain at least 1 uppercase letter");
        }

        if (!password.matches(".*[!@#$%].*")){
            errors.reject("password.no_uppercase", "The password must contain at least 1 [!@#$%] letter");
        }
    }

    private void validateName(Errors errors, UserCreateForm form) {

        if (form.getName().isEmpty()){
            errors.reject("name.empty", "Empty name");
        }

        if (userService.findByName(form.getName()) != null) {
            errors.reject("name.exists", "User with this name already exists");
        }
    }
}
