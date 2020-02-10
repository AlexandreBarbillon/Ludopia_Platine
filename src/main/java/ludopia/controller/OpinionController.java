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

/**
 * Controller servant a afficher tout ce qui est relatif aux opinions
 */
@Controller
public class OpinionController {
    private UserService userService;
    private OpinionService opinionService;
    public OpinionController(UserService userService, OpinionService opinionService) {
        this.userService = userService;
        this.opinionService = opinionService;
    }

    /**
     * Permet de créer un opinion
     * @param gameId l'id du jeu
     * @param noteStr la note donnée
     * @param message le message écrit
     * @return une redirection vers la page du jeu
     */
    @PostMapping("/opinion/create/{gameId}")
    public String addOpinion(@PathVariable int gameId, String noteStr, String message){
        int note;
        if (noteStr == null) note = 0;
        else note = Integer.parseInt(noteStr);
        LudopiaUser potentialUser = userService.getCurrentUser();
        if (potentialUser != null && note > 0 && note <= 5){
            int userId = potentialUser.getId();
            Opinion opinion = opinionService.createOpinion(userId,gameId,note,message);
            if(opinion != null){
                return "redirect:/game/"+gameId;
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
