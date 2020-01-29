package ludopia.objects.list.repository;

import ludopia.objects.list.GameList;
import ludopia.objects.list.OwnerType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ListRepository extends CrudRepository<GameList, String> {
    Iterable<GameList> findByIdOwnerAndOwnerType(int idOwner, OwnerType ownerType);
    Optional<GameList> findById(int id);
}
