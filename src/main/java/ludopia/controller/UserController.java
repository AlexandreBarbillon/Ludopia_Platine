package ludopia.controller;

import ludopia.objects.associations.Association;
import ludopia.objects.associations.service.AssociationService;
import ludopia.objects.users.LudopiaUser;
import ludopia.objects.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/create")
    public String registration(Model model) {
        model.addAttribute("userForm", new LudopiaUser());
        return "registration";
    }

    @PostMapping("/user/create")
    public String submit(@ModelAttribute("userForm") LudopiaUser user) {
        userService.createUser(user,user.getPassword());
        return "index";
    }

    @GetMapping("/user/{userId}")
    public ModelAndView getProfile(@PathVariable("userId") int userId) {
        ModelAndView mav = new ModelAndView("user");
        LudopiaUser user = userService.getUserById(userId);

        /*TEMPORAIRE POUR TESTS*/
        /*_____________________________*/
/*
        LudopiaUser user = new LudopiaUser();
        user.setUsername("herbert");
        String password = "oui";
        user.setPassword(password);
        user.setDescription("okamari no suzoki okamari no suzoki okamari no suzoki okamari no suzoki okamari no suzoki okamari no suzoki okamari no suzoki okamari no suzoki okamari no suzoki ");

        userService.createUser(user,password);
*/

        /*_____________________________*/

        List<String> actual_lists = new ArrayList<>();
        actual_lists.add("Mes jeux préférés");

        mav.addObject("user", user);
        mav.addObject("lists", actual_lists);

        return mav;
    }
}
