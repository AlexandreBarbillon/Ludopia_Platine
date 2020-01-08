package ludopia.controller;

import ludopia.objects.users.LudopiaUser;
import ludopia.objects.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/registration")
    private String registration(Model model) {
        model.addAttribute("userForm", new LudopiaUser());
        return "registration";
    }

    @PostMapping("/registration")
    private String registration(@ModelAttribute("userForm") LudopiaUser user, Model model) {
        userService.createUser(user);
        return "redirect:/index";
    }
}
