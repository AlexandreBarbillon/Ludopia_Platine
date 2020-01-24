package ludopia.objects.associations.repository;

import ludopia.objects.associations.Association;
import ludopia.objects.games.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AssociationRepository extends CrudRepository<Association, String> {
    Optional<Association> findById(int id);
    @Query("FROM Association as asso JOIN GameList as list ON asso.possessedGamesList = list.id WHERE :gameId MEMBER OF list.gameList")
    List<Association> findAssoHavingTheGame(@Param("gameId") int gameId);
}
