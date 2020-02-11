package ludopia.controller;

import ludopia.objects.associations.Association;
import ludopia.objects.associations.service.AssociationService;
import ludopia.objects.games.Game;
import ludopia.objects.games.service.GameService;
import ludopia.objects.list.exceptions.GameAlreadyInListException;
import ludopia.objects.list.service.ListService;
import ludopia.objects.users.LudopiaUser;
import ludopia.objects.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ListController {
    private final
    ListService listService;
    private final
    UserService userService;
    private final
    AssociationService assoService;
    private final
    GameService gameService;

    public ListController(ListService listService, UserService userService, AssociationService assoService, GameService gameService) {
        this.listService = listService;
        this.userService = userService;
        this.assoService = assoService;
        this.gameService = gameService;
    }

    @PostMapping("/list/addToAsso")
    public String addGameToAsso(Integer assoId, Integer gameId){
        System.out.println("asso : "+assoId+" | "+"game : "+gameId);
        LudopiaUser user = userService.getCurrentUser();
        Association asso = assoService.getAssoById(assoId);
        Game game = gameService.getGameById(gameId);
        if(user != null && game != null) {
            int userId = user.getId();
            if (userId == asso.getAdmin()) {
                try {
                    listService.addGameToList(asso.getPossessedGamesList(), gameId);
                } catch (GameAlreadyInListException e) {

                }

            }
        }
        return "redirect:/game/"+gameId;
    }

    @GetMapping("/list/addToUser/{id}")
    public String addGameToUser(@PathVariable int id){
        if(gameService.getGameById(id) != null)
            userService.addToUserList(id);
        return "redirect:/game/"+id;
    }

    @GetMapping("/list/removeFromUser/{id}")
    public String removeGameFromUser(@PathVariable int id){
        if(gameService.getGameById(id) != null)
            userService.removeFromUserList(id);
        return "redirect:/game/"+id;
    }
}
