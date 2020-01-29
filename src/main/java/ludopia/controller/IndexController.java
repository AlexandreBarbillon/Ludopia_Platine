package ludopia.controller;

import ludopia.objects.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class IndexController {

    private GameService gameService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        var games = gameService.getGamesSortByDate(15);
        var first5Games = new ArrayList<>();
        var second5Games = new ArrayList<>();
        var third5Games = new ArrayList<>();
        for (int i = 0; i < games.size()-10; i++) {

            first5Games.add(games.get(i));
        }
        for (int i = 5; i < games.size()-5; i++) {
            second5Games.add(games.get(i));
        }
        for (int i = 10; i < games.size(); i++) {
            third5Games.add(games.get(i));
        }

        mav.addObject("gamesFirst", first5Games);
        mav.addObject("gamesSecond", second5Games);
        mav.addObject("gamesThird", third5Games);


        return mav;
    }

    @Autowired
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }
}