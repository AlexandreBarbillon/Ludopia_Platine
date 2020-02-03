package ludopia.objects.opinion.repository;

import ludopia.objects.opinion.Opinion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OpinionRepository extends CrudRepository<Opinion, String> {
    /**
     * retourne tous les avis sur un jeu
     * @param gameId l'identifiant du jeu
     * @return une liste d'Opinion
     */
    List<Opinion> getAllByGameId(int gameId);

    /**
     * retourne tous les avis d'un utilisateur
     * @param userId l'identifiant de l'utilisateur
     * @return une liste d'Opinion
     */
    List<Opinion> getAllByUserId(int userId);

    /**
     * Retourne l'opinion d'un utilisateur sur un jeu donnée (utilisé pour vérifier qu'un utilisateur n'a pas déjà donné son avis sur un jeu
     * @param userId l'identifiant de l'utilisateur
     * @param gameId l'identifiant du jeu
     * @return un objet Optional contenant potentiellement un Opinion
     */
    Optional<Opinion> findOpinionByUserIdAndGameId(int userId, int gameId);
}
