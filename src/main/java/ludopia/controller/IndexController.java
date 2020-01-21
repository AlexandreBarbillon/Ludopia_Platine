package ludopia.controller;

import ludopia.objects.games.Game;
import ludopia.objects.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    List<Game> first5Games;
    private List<Game> second5Games;
    private List<Game> third5Games;
    private GameService gameService;

    @GetMapping("/infos")
    public ModelAndView infos() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("informations");
        return mav;
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        var games = gameService.getGamesSortByDate(15);
        first5Games = new ArrayList<>();
        second5Games = new ArrayList<>();
        third5Games = new ArrayList<>();
        for (int i = 0; i < games.size()-10; i++) {

            first5Games.add(games.get(i));
        }
        for (int i = 5; i < games.size()-5; i++) {
            second5Games.add(games.get(i));
        }
        for (int i = 10; i < games.size(); i++) {
            third5Games.add(games.get(i));
        }

        System.out.println(first5Games.size());
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