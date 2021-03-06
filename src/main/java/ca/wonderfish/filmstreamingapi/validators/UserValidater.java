package ca.wonderfish.filmstreamingapi.validators;

import ca.wonderfish.filmstreamingapi.domains.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidater implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User)target;
        if(user.getPassword().length()<6){
            errors.rejectValue("password","Length","password must be at least 6 characters");
        }

        if(!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("password","Match","password and confirmpassword don't match");
        }
    }
}
