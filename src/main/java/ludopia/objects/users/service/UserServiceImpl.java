package ludopia.objects.users.service;

import ludopia.objects.users.User;
import ludopia.objects.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserServiceImpl control the user creation/recover/update/deleting
 */
@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Get all the users in database
     * @return all the users
     */
    @Override
    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }

    /**
     * Get one user by his username
     * @param username the username of the searched user
     * @return the user if found, null otherwise
     */
    @Override
    public User getUser(String username) {
        Optional<User> possibleUser = userRepo.findById(username);
        return possibleUser.orElse(null);
    }

    /**
     * Create a new user in database
     * @param user the user to create
     * @return the user created
     */
    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    /**
     * Remove the user found in database
     * @param username the username of the user
     */
    @Override
    public void removeUser(String username) {
        userRepo.deleteById(username);
    }
}
