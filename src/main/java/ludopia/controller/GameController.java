package ludopia.controller;

import ludopia.objects.games.Game;
import ludopia.objects.games.service.GameService;
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
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping("/addGame")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("gameCreation");
        return mv;
    }

    @PostMapping("/addGame")
    public ModelAndView addGame(Game game) {
        ModelAndView mv = new ModelAndView("gameCreation");
        gameService.createGame(game);
        System.out.println(gameService.getGamesSortByDate(3));
        return mv;
    }
}
