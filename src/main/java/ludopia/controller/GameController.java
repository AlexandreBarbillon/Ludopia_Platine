package ludopia.controller;

import ludopia.objects.games.Game;
import ludopia.objects.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping("/game/create")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("gameCreation");
        return mv;
    }

    @PostMapping("/game/create")
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
        return mv;
    }

}
