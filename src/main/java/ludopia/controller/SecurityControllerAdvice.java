package ludopia.controller;

import ludopia.objects.users.LudopiaUser;
import ludopia.objects.users.service.UserService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class SecurityControllerAdvice {

    private UserService userService;

    @ModelAttribute("user")
    LudopiaUser getCurrentUser() {
        return userService.getCurrentUser();
    }
}
