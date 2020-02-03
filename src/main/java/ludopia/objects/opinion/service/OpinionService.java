package ludopia.objects.opinion.service;

import ludopia.objects.opinion.Opinion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OpinionService {
    /**
     * Récupère tous les avis d'un utilisateur
     * @param userId l'identifiant de l'utilisateur
     * @return une liste d'Opinions
     */
    List<Opinion> getAllOpinionFromUser(int userId);

    /**
     * Récupère tous les avis sur un jeu
     * @param gameId l'identifiant du jeu
     * @return une liste d'Opinion
     */
    List<Opinion> getAllOpinionFromGame(int gameId);

    /**
     * Crée un opinion
     * @param userId l'identifiant de l'utilisateur
     * @param gameId l'identifiant du jeu sur lequel l'utilisateur donne son avis
     * @param note   la note du jeu (entre 1 et 5)
     * @param message un commentaire
     * @return l'opinion créé
     */
    Opinion createOpinion(int userId, int gameId, int note, String message);

    /**
     * permet de savoir si un utilisateur a déjà donné son avis sur le jeu
     * @param userId l'identifiant de l'utilisateur
     * @param gameId l'identifiant du jeu
     * @return true si l'avis existe, false sinon
     */
    boolean IsUserAlreadyHaveAnOpinionOnGame(int userId, int gameId);
}
