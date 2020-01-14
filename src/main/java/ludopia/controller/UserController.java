package ludopia.controller;

import ludopia.objects.users.LudopiaUser;
import ludopia.objects.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new LudopiaUser());
        return "registration";
    }

    @PostMapping("/registration")
    public String submit(@ModelAttribute("userForm") LudopiaUser user) {
        userService.createUser(user);
        return "index";
    }
}
