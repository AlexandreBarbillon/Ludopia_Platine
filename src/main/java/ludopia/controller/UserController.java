package ludopia.controller;

import ludopia.objects.games.Game;
import ludopia.objects.games.service.GameService;
import ludopia.objects.list.service.ListService;
import ludopia.objects.users.LudopiaUser;
import ludopia.objects.users.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * Contrôle ce qui est relatif à l'utilisateur
 */
@Controller
public class UserController {

    private final UserService userService;
    private final GameService gameService;
    public UserController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

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
        List<Game> games = null;
        if(user != null)
            games = gameService.unwrapGameList(user.getGameList());
        
        mav.addObject("user", user);
        mav.addObject("games", games);

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
