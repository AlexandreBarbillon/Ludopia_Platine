package ludopia.objects.associations.repository;

import ludopia.objects.associations.Association;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository pour les Associations
 */
public interface AssociationRepository extends CrudRepository<Association, String> {
    /**
     * Recupère l'association correspondant à l'id donné
     * @param id l'ID de l'association
     * @return un objet Optional contenant potentiellement une association
     */
    Optional<Association> findById(int id);

    /**
     * Récupère les associations possédant un jeu
     * @param gameId l'ID d'un jeu
     * @return une liste des associations comprenant ce jeu
     */
    @Query("FROM Association as asso JOIN GameList as list ON asso.possessedGamesList = list.id WHERE :gameId MEMBER OF list.games")
    List<Association> findAssoHavingTheGame(int gameId);

    /**
     * Récupère les associations dont l'utilisateur donnée en paramètre est l'administrateur
     * @param userId l'id de l'utilisateur
     * @return une liste d'Associations
     */
    List<Association> findAssociationsByAdmin(int userId);
}
