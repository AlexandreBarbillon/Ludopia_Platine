package ludopia.controller;

import ludopia.objects.opinion.Opinion;
import ludopia.objects.opinion.service.OpinionService;
import ludopia.objects.users.LudopiaUser;
import ludopia.objects.users.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OpinionController {
    private UserService userService;
    private OpinionService opinionService;
    public OpinionController(UserService userService, OpinionService opinionService) {
        this.userService = userService;
        this.opinionService = opinionService;
    }

    @PostMapping("/opinion/create/{gameId}")
    public String addOpinion(@PathVariable int gameId, int note, String message){
        LudopiaUser potentialUser = userService.getCurrentUser();
        if (potentialUser != null && note > 0 && note < 5){
            int userId = potentialUser.getId();
            Opinion opinion = opinionService.createOpinion(userId,gameId,note,message);
            if(opinion != null){
                return "opinionRegistered";
            }
            else{
                return "opinionUnregistered";
            }
        }
        else{
            return "opinionUnregistered";
        }
    }
}
