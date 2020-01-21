package ludopia.controller;

import ludopia.objects.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InfoController {
    private GameService gameService;

    @GetMapping("/infos")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("informations");
        return mav;
    }
    

}