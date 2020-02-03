package ludopia.objects.users.repository;

import ludopia.objects.users.LudopiaUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<LudopiaUser, Integer> {
    Optional<LudopiaUser> findByUsername(String username);
    Optional<LudopiaUser> findById(int id);
}
