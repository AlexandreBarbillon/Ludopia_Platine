package ludopia.controller;

import ludopia.objects.associations.Association;
import ludopia.objects.associations.service.AssociationService;
import ludopia.objects.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
/**
 * Controller des Associations, il s'occupe d'afficher toutes les pages relatives aux associations
 */
public class AssociationController {

    private final
    AssociationService associationService;
    private final
    GameService gameService;

    public AssociationController(AssociationService associationService, GameService gameService) {
        this.associationService = associationService;
        this.gameService = gameService;
    }

    /**
     * Affichage du formulaire de création d'une association
     * @return un ModelAndView
     */
    @GetMapping("/association/create")
    public ModelAndView displayAssoCreationPage() {
        return new ModelAndView("associationCreation");
    }
    /**
     * Réception du formulaire
     * @return un ModelAndView
     */
    @PostMapping("/association/create")
    public String createNewAssociation(Association asso) {
        ModelAndView mv = new ModelAndView("associationCreation");
        Association createdAsso = associationService.createAssociation(asso);
        return "redirect:/association/"+createdAsso.getId();
    }

    /**
     * Affichage de la page d'un jeu
     * @param id l'id d'une association
     * @return un ModelAndView
     */
    @GetMapping("/association/{id}")
    public ModelAndView displayAsso(@PathVariable int id){
        ModelAndView mv = new ModelAndView("associationPage");
        Association asso = associationService.getAssoById(id);
        if (asso!= null) {
            gameService.unwrapGameList(asso.getPossessedGamesList());
        }
        mv.addObject("asso",asso);
        mv.addObject("gameList",asso);
        return mv;
    }

    /**
     * L'affichage de la carte des associations
     * @return un String avec le nom du layout a afficher
     */
    @GetMapping("/association/map")
    public String displayAssoMap(){
        return "assoMap";
    }
}
