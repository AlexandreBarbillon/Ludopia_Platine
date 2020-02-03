package ludopia.objects.users.repository;

import ludopia.objects.users.CredentialUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CredentialUserRepository extends CrudRepository<CredentialUser, String> {
    /**
     * récupère un CredentialUser par son nom d'utilisateur
     * @param username le nom d'utilisateur
     * @return un objet Optional contenant potentiellement un CredentialUser
     */
    Optional<CredentialUser> findByUsername(String username);
}
