package ludopia.controller;

import ludopia.objects.associations.Association;
import ludopia.objects.associations.service.AssociationService;
import ludopia.objects.games.Game;
import ludopia.objects.games.service.GameService;
import ludopia.objects.users.LudopiaUser;
import ludopia.objects.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    AssociationService assoService;

    @GetMapping("/addGame")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("gameCreation");
        return mv;
    }

    @PostMapping("/addGame")
    public ModelAndView addGame(Game game) {
        ModelAndView mv = new ModelAndView("gameCreation");
        gameService.createGame(game);
        return mv;
    }

    @GetMapping("/game/{id}")
    public ModelAndView displayGame(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("game");
        mv.addObject("oneGame", gameService.getGameById(id));
        var list = new ArrayList<>();
        var list2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add("star");
        }
        for (int i = 4; i < 5; i++) {
            list2.add("starEmpty");
        }
        mv.addObject("stars", list);
        mv.addObject("starsEmpty", list2);

        System.out.println(assoService.findAssoHavingTheGame(id).size());
        mv.addObject("assos", assoService.findAssoHavingTheGame(id));
        return mv;
    }

}
