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

    @Autowired
    private AssociationService associationService;

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

        /*TEMPORAIRE POUR TESTS*/
        /*_____________________________*/

        /*

        LudopiaUser user = new LudopiaUser();
        user.setId(1);
        user.setUsername("herbert");
        user.setDescription("okamari no suzoki okamari no suzoki okamari no suzoki okamari no suzoki okamari no suzoki okamari no suzoki okamari no suzoki okamari no suzoki okamari no suzoki ");

        LudopiaUser otherUser = new LudopiaUser();
        otherUser.setId(2);
        otherUser.setUsername("raymond");

        userService.createUser(otherUser);

        user.addFriend(2);

        Association assoc = new Association();
        assoc.setId(10);
        assoc.setName("Des jeux de folie !");
        assoc.setLogo_img_link("https://media.begeek.fr/2019/12/garfield-660x369.jpg");

        associationService.createAssociation(assoc);

        user.addAssociation(10);


         */
        /*_____________________________*/


        List<LudopiaUser> actual_friends = new ArrayList<>();
        for (int f : user.getFriends()) {
            actual_friends.add(userService.getUserById(f));
        }

        List<Association> actual_assoc = new ArrayList<>();
        for (int a : user.getAssociations()) {
            actual_assoc.add(associationService.getAssoById(a));
        }

        /*temporaire*/
        List<String> actual_lists = new ArrayList<>();
        actual_lists.add("Mes jeux préférés");

        mav.addObject("user", user);
        mav.addObject("friends", actual_friends);
        mav.addObject("associations", actual_assoc);
        mav.addObject("lists", actual_lists);
        System.out.println(mav.getModel().get("user"));
        
        System.out.println(mav.getModel().get("friends") + " /// " + mav.getModel().get("associations") + " /// " + mav.getModel().get("lists   "));
        return mav;
    }
}
