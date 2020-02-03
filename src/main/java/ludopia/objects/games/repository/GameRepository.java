package ludopia.objects.games.repository;

import ludopia.objects.games.Game;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository pour les jeux
 */
public interface GameRepository extends CrudRepository<Game, String> {
    /**
     * Permet de récupérer un jeu par son ID
     * @param id l'id d'un jeu
     * @return un objet Optional contenant potentiellement un jeu
     */
    Optional<Game> findById(int id);

    /**
     * Récupère la liste des jeux par ordre d'entrée en base décroissante
     * @param pageable un objet Pageable permettant de récupérer un nombre limité de jeux
     * @return une liste de jeux
     */
    List<Game>  findByOrderByAddDateDesc(Pageable pageable);

    /**
     * Recherche des jeux par leur nom
     * @param search la chaîne a rechercher dans les noms
     * @return une liste de jeux répondant à la recherche
     */
    @Query("FROM Game WHERE name LIKE %:search%")
    List<Game> searchGames(String search);
}
