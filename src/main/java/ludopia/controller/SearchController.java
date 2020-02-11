package ludopia.controller;

import ludopia.objects.games.Game;
import ludopia.objects.games.service.GameService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Permet tout ce qui est relatif a la recherche de jeux
 */
@Controller
public class SearchController {
    private GameService gameService;

    public SearchController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Affiche les résultats de la recherche de jeux
     * @param searchString le nom du jeu a rechercher
     * @return un modelAndView contenant les jeux récupérés
     */
    @GetMapping("/search")
    public ModelAndView displaySearchResult(@Param("searchString") String searchString){
        List<Game> games;
        if(searchString == null || searchString.isEmpty()){
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
