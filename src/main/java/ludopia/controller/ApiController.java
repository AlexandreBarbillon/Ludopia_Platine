package ludopia.controller;

import ludopia.objects.associations.Association;
import ludopia.objects.associations.service.AssociationService;
import ludopia.objects.games.Game;
import ludopia.objects.games.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    private AssociationService assoService;
    private GameService gameService;
    public ApiController(AssociationService assoService, GameService gameService){
        this.gameService = gameService;
        this.assoService = assoService;
    }

    @GetMapping("/assoList")
    public Iterable<Association> getAllAsso(){
        return this.assoService.getAll();
    }

    @GetMapping("/searchGame")
    public Iterable<Game> searchGames(String search){
        return this.gameService.searchGame(search);
    }
}
