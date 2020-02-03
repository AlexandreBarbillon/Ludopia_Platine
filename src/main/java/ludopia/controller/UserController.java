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

/**
 * Contrôle ce qui est relatif à l'utilisateur
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Affiche le formulaire de création d'utilisateur
     * @param model
     * @return la page d'enregistrement
     */
    @GetMapping("/user/create")
    public String registration(Model model) {
        if (userService.getCurrentUser() != null) {
            return "redirect:/logout";
        } else {
            model.addAttribute("userForm", new LudopiaUser());
            return "registration";
        }
    }

    @PostMapping("/user/create")
    public String submit(@ModelAttribute("userForm") LudopiaUser user) {
        LudopiaUser savedUser = userService.createUser(user,user.getPassword());
        if (savedUser == null) {
            return "registration";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/user/{userId}")
    public ModelAndView getProfile(@PathVariable("userId") int userId) {
        ModelAndView mav;

        if (userService.getCurrentUser() != null
                && userService.getCurrentUser().getId() == userId) {
            mav = new ModelAndView("loggedUser");
        } else {
            mav = new ModelAndView("user");
        }
        LudopiaUser user = userService.getUserById(userId);

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

    @GetMapping("/user/edit")
    public String updateUser(Model model) {
        if (userService.getCurrentUser() == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("userEditForm", userService.getCurrentUser());
            return "userEdition";
        }
    }

    @PostMapping("/user/edit")
    public String submitModif(@ModelAttribute("userEditForm") LudopiaUser user) {
        LudopiaUser loggedUser = userService.getCurrentUser();
        loggedUser.setDescription(user.getDescription());
        loggedUser.setImageLink(user.getImageLink());
        userService.updateUser(loggedUser);
        return "redirect:/user/" + loggedUser.getId();
    }
}
