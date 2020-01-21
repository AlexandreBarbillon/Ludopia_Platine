package ludopia.controller;

import ludopia.objects.users.LudopiaUser;
import ludopia.objects.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.ArrayList;

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

    @GetMapping("/profile/{user}")
    public ModelAndView getProfile(@PathParam("user") String username) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("profile");
        LudopiaUser user = userService.getUserByUsername(username);
        mav.addObject("user", user);
        mav.addObject("friends", user.getFriends());
        mav.addObject("associations", user.getAssociations());
        mav.addObject("lists", user.getLists());
        return mav;
    }
}
