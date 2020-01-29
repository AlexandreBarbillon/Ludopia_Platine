package ludopia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InfoController {

    @GetMapping("/infos")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("informations");
        return mav;
    }

}