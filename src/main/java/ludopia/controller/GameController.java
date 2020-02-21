package ludopia.controller;

import ludopia.objects.associations.Association;
import ludopia.objects.associations.service.AssociationService;
import ludopia.objects.games.Game;
import ludopia.objects.games.service.GameService;
import ludopia.objects.list.service.ListService;
import ludopia.objects.opinion.Opinion;
import ludopia.objects.opinion.service.OpinionService;
import ludopia.objects.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GameController {

    private GameService gameService;
    private OpinionService opinionService;
    private UserService userService;
    @Autowired private ListService listService;
    public GameController(GameService gameService, OpinionService opinionService, UserService userService) {
        this.gameService = gameService;
        this.opinionService = opinionService;
        this.userService = userService;
    }

    @Autowired
    AssociationService assoService;

    /**
     * Affiche le formulaire de création d'un jeu
     * @return un ModelAndView
     */
    @GetMapping("/game/create")
    public ModelAndView gameCreationForm() {
        return new ModelAndView("gameCreation");
    }

    /**
     * Récupère le résultat du formulaire de création d'un jeu puis redirige vers la page du jeu créé
     * @param game le jeu a crée
     * @return la redirection vers la page du jeu créé
     */
    @PostMapping("/game/create")
    public String addGame(Game game) {
        Game newGame = gameService.createGame(game);
        return "redirect:/game/"+newGame.getId();
    }

    /**
     * Affichage de la page d'un jeu
     * @param id id du jeu a afficher
     * @return un modelAndView contenant les données du jeu et la liste des avis sur le jeu
     */
    @GetMapping("/game/{id}")
    public ModelAndView displayGame(@PathVariable("id") int id) {
        var loggedUser = userService.getCurrentUser();
        ModelAndView mv = new ModelAndView("game");
        List<Opinion> opinions = opinionService.getAllOpinionFromGame(id);
        List<OpinionUser> opinionUsers = new ArrayList<>();
        int sum = 0;
        for (Opinion opinion: opinions) {
            opinionUsers.add(new OpinionUser(opinion));
            sum+=opinion.getNote();
        }
        int avg;
        if (!opinions.isEmpty()) avg = sum/opinions.size();
        else avg = 0;

        mv.addObject("opinions", opinionUsers);
        mv.addObject("oneGame", gameService.getGameById(id));
        var list = new ArrayList<>();
        var list2 = new ArrayList<>();
        for (int i = 0; i < avg; i++) {
            list.add("star");
        }
        for (int i = avg; i < 5; i++) {
            list2.add("starEmpty");
        }
        mv.addObject("stars", list);
        mv.addObject("starsEmpty", list2);

        mv.addObject("assos", assoService.findAssoHavingTheGame(id));
        if (loggedUser != null) {
            List<Association> assosUser;
            assosUser = assoService.findAssoFromUser(loggedUser.getId());
            mv.addObject("assosUser", assosUser);
            mv.addObject("isGameInUserList", listService.gameInList(loggedUser.getGameList(), id));
        }



        return mv;
    }

    /**
     * Objet privé permettant de relier l'opinion (qui contient le message et la note d'un user ainsi que son ID) au nom de l'utilisateur qui a donné l'avis.
     */
    private class OpinionUser{
        int userId;
        String username;
        String avatarLink;
        int note;
        String message;

        OpinionUser(Opinion opinion){
            this.userId = opinion.getUserId();
            this.note = opinion.getNote();
            this.message = opinion.getMessage();
            this.username = userService.getUserById(opinion.getUserId()).getUsername();
            this.avatarLink = userService.getUserById(opinion.getUserId()).getImageLink();
        }

        public int getUserId() {
            return userId;
        }

        public String getAvatarLink() {
            return avatarLink;
        }

        public String getUsername() {
            return username;
        }

        public int getNote() {
            return note;
        }

        public String getMessage() {
            return message;
        }
    }
}
