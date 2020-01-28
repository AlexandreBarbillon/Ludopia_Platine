package ludopia.controller;

import ludopia.objects.games.Game;
import ludopia.objects.games.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchController {
    private GameService gameService;

    public SearchController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/search")
    public ModelAndView displaySearchResult(@RequestParam String searchString){
        List<Game> games;
        if(searchString.isEmpty()){
            games = gameService.getGamesSortByDate();
        }
        else{
            games = gameService.searchGame(searchString);
        }
        ModelAndView mv = new ModelAndView("searchPage");
        mv.addObject("searchResult", games);
        return mv;
    }
}
