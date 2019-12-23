package ludopia.objects.users.repository;

import ludopia.objects.users.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
