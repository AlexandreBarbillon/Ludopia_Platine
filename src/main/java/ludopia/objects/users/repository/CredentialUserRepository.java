package ludopia.objects.users.repository;

import ludopia.objects.users.CredentialUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CredentialUserRepository extends CrudRepository<CredentialUser, String> {
    Optional<CredentialUser> findByUsername(String username);
}
