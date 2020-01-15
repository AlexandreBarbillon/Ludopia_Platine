package ludopia.controller;

import ludopia.objects.associations.Association;
import ludopia.objects.associations.service.AssociationService;
import ludopia.objects.games.Game;
import ludopia.objects.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AssociationController {

    @Autowired
    AssociationService associationService;

    @GetMapping("/addAsso")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("associationCreation");
        return mv;
    }

    @PostMapping("/addAsso")
    public ModelAndView registerNewTrainer(Association asso) {
        ModelAndView mv = new ModelAndView("associationCreation");
        associationService.createAssociation(asso);
        return mv;
    }
}
