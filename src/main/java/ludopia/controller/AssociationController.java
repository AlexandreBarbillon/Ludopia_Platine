package ludopia.controller;

import ludopia.objects.associations.Association;
import ludopia.objects.associations.service.AssociationService;
import ludopia.objects.games.Game;
import ludopia.objects.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class AssociationController {

    @Autowired
    AssociationService associationService;
    @Autowired
    GameService gameService;
    @GetMapping("/association/create")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("associationCreation");
        return mv;
    }

    @PostMapping("/association/create")
    public ModelAndView registerNewTrainer(Association asso) {
        ModelAndView mv = new ModelAndView("associationCreation");
        associationService.createAssociation(asso);
        return mv;
    }
    @GetMapping("/association/{id}")
    public ModelAndView displayAsso(@PathVariable int id){
        ModelAndView mv = new ModelAndView("associationPage");
        Association asso = associationService.getAssoById(id);
        List<Game> assoGameList = gameService.unwrapGameList(asso.getPossessedGamesList());
        mv.addObject("asso",asso);
        mv.addObject("gameList",asso);
        return mv;
    }
    @GetMapping("/association/map")
    public String displayAssoMap(){
        return "assoMap";
    }
}
