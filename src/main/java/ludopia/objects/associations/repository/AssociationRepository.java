package ludopia.objects.associations.repository;

import ludopia.objects.associations.Association;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AssociationRepository extends CrudRepository<Association, Integer> {
    Optional<Association> findById(int id);
    @Query("FROM Association as asso JOIN GameList as list ON asso.possessedGamesList = list.id WHERE :gameId MEMBER OF list.games")
    List<Association> findAssoHavingTheGame(int gameId);
    List<Association> findAssociationsByAdmin(int userId);
}
