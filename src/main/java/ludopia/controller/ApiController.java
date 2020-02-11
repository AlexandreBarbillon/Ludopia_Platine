package ludopia.controller;

import ludopia.objects.associations.Association;
import ludopia.objects.associations.service.AssociationService;
import ludopia.objects.games.Game;
import ludopia.objects.games.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
/**
 * ApiController permet d'afficher des informations utiles au format JSON.
 * Elle est notamment interrogée lorsque l'application doit actualiser des informations en direct.
 * Elle passera donc par du Javascript qui ira interrogé cette API interne pour récupérer des informations utiles
 */
public class ApiController {
    private AssociationService assoService;
    private GameService gameService;
    public ApiController(AssociationService assoService, GameService gameService){
        this.gameService = gameService;
        this.assoService = assoService;
    }

    /**
     * Renvoie la liste des associations stockées en base de données.
     * @return un Iterable d'Association
     */
    @GetMapping("/assoList")
    public Iterable<Association> getAllAsso(){
        return this.assoService.getAll();
    }

    @GetMapping("/assoList/{id}")
    public Iterable<Association> getAssoWithGame(@PathVariable int id){
        return this.assoService.findAssoHavingTheGame(id);
    }
}
