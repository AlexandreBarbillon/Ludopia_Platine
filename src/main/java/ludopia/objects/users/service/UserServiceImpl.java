package ludopia.objects.users.service;

import ludopia.objects.users.CredentialUser;
import ludopia.objects.users.LudopiaUser;
import ludopia.objects.users.repository.CredentialUserRepository;
import ludopia.objects.users.repository.UserRepository;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

/**
 * UserServiceImpl control the user creation/recover/update/deleting
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepo;
    private CredentialUserRepository credUserRepo;
    public UserServiceImpl(UserRepository userRepo, CredentialUserRepository credUserRepo) {
        this.userRepo = userRepo;
        this.credUserRepo = credUserRepo;
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
    public LudopiaUser createUser(LudopiaUser ludopiaUser, String password) {
        LudopiaUser savedUser = userRepo.save(ludopiaUser);
        CredentialUser user = new CredentialUser(ludopiaUser.getUsername(),password, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), savedUser.getId());
        credUserRepo.save(user);
        return savedUser;
    }

    @Override
    public CredentialUser getAuthentificationUserByUsername(String username) {
        return credUserRepo.findByUsername(username).orElse(null);
    }


    /**
     * Remove the user found in database
     * @param username the username of the user
     */
    @Override
    public void removeUser(String username) {
        userRepo.deleteById(username);
    }

    @Override
    public LudopiaUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object user = authentication.getPrincipal();
        if(user instanceof CredentialUser){
            CredentialUser credUser = (CredentialUser) user;
            return getUserById(credUser.getUserId());
        }
        else{
            return null;
        }
    }
}
