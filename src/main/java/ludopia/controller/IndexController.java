package ludopia.controller;

import ludopia.objects.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    private GameService gameService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("gamesByDate",gameService.getGamesSortByDate(5));
        return mav;
    }

    @Autowired
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }
}