package ludopia.controller;

import ludopia.objects.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class SecurityControllerAdvice {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    Object getCurrentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var principal = auth.getPrincipal();
        if ((userService.getCurrentUser()) == null) {
            return principal;
        } else {
            return userService.getCurrentUser();
        }
    }
}
