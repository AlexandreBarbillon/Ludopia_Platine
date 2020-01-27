package ludopia.objects.associations.repository;

import ludopia.objects.associations.Association;
import ludopia.objects.games.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AssociationRepository extends CrudRepository<Association, Integer> {
    Optional<Association> findById(int id);
}
