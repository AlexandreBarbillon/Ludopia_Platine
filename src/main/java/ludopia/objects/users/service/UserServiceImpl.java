package ludopia.objects.users.service;

import ludopia.objects.users.LudopiaUser;
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
    public Iterable<LudopiaUser> getAllUsers() {
        return userRepo.findAll();
    }

    /**
     * Get one user by his username
     * @param userId the username of the searched user
     * @return the user if found, null otherwise
     */
    @Override
    public LudopiaUser getUserById(int userId) {
        Optional<LudopiaUser> possibleUser = userRepo.findById(userId);
        return possibleUser.orElse(null);
    }

    @Override
    public LudopiaUser getUserByUsername(String username){
        return userRepo.findByUsername(username).orElse(null);
    }

    /**
     * Create a new user in database
     * @param ludopiaUser the user to create
     * @return the user created
     */
    @Override
    public LudopiaUser createUser(LudopiaUser ludopiaUser) {
        return userRepo.save(ludopiaUser);
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
