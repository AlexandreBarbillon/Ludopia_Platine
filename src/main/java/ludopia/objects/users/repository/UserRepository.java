package ludopia.objects.users.repository;

import ludopia.objects.users.LudopiaUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<LudopiaUser, Integer> {
    /**
     * Retrouve un LudopiaUser par son nom d'utilisateur
     * @param username nom d'utilisateur
     * @return un objet Optional contenant potentiellement un LudopiaUser
     */
    Optional<LudopiaUser> findByUsername(String username);

    /**
     * Retrouve un LudopiaUser par son identifiant
     * @param id l'identifiant de l'utilisateur
     * @return un objet Optional contenant potentiellement un LudopiaUser
     */
    Optional<LudopiaUser> findById(int id);
}
