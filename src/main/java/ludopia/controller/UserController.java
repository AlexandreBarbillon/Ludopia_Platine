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

        List<GameList> lists = new ArrayList<>();
        /*
        for (int i : user.getLists()) {
            actual_lists.add(listService.getListById(i));
        }
        */



        GameList testList = new GameList(31, OwnerType.USER, "Mes jeux",  "Je les aime mes jeux");
        try {
            testList.addGameToList(10);
        } catch (GameAlreadyInListException e) {
            e.printStackTrace();
        }
        GameList testList2 = new GameList(31, OwnerType.USER, "Mes jeux préférés",  "Je les aime encore plus mes jeux");
        try {
            testList2.addGameToList(11);
        } catch (GameAlreadyInListException e) {
            e.printStackTrace();
        }



        lists.add(testList);
        lists.add(testList2);

        mav.addObject("user", user);
        mav.addObject("lists", lists);

        return mav;
    }
}
