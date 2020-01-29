package ludopia.controller;

import ludopia.objects.associations.Association;
import ludopia.objects.associations.service.AssociationService;
import ludopia.objects.games.Game;
import ludopia.objects.list.GameList;
import ludopia.objects.list.OwnerType;
import ludopia.objects.list.exceptions.GameAlreadyInListException;
import ludopia.objects.list.service.ListService;
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

    @Autowired
    private ListService listService;

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

        for (LudopiaUser u : userService.getAllUsers()) {
            System.out.println(u.getId()+"//"+u.getUsername());
        }

        List<GameList> lists = new ArrayList<>();
        if (user!=null) {
            for (int i : user.getLists()) {
                lists.add(listService.getListById(i));
            }
        }
        
        mav.addObject("user", user);
        mav.addObject("lists", lists);

        return mav;
    }
}
