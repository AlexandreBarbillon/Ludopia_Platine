package ludopia.objects.users.service;

import ludopia.objects.list.GameList;
import ludopia.objects.list.OwnerType;
import ludopia.objects.list.exceptions.GameAlreadyInListException;
import ludopia.objects.list.service.ListService;
import ludopia.objects.users.CredentialUser;
import ludopia.objects.users.LudopiaUser;
import ludopia.objects.users.repository.CredentialUserRepository;
import ludopia.objects.users.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * UserServiceImpl control the user creation/recover/update/deleting
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepo;
    private CredentialUserRepository credUserRepo;
    private final ListService listService;
    public UserServiceImpl(UserRepository userRepo, CredentialUserRepository credUserRepo, ListService listService) {
        this.userRepo = userRepo;
        this.credUserRepo = credUserRepo;
        this.listService = listService;
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
        if (getUserByUsername(ludopiaUser.getUsername()) != null) {
            return null;
        }
        LudopiaUser savedUser = userRepo.save(ludopiaUser);
        CredentialUser user = new CredentialUser(ludopiaUser.getUsername(),password, savedUser.getId());
        credUserRepo.save(user);
        return savedUser;
    }

    @Override
    public CredentialUser getAuthentificationUserByUsername(String username) {
        return credUserRepo.findByUsername(username).orElse(null);
    }


    /**
     * Remove the user found in database
     * @param id the username of the user
     */
    @Override
    public void removeUser(int id) {
        userRepo.deleteById(id);
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

    @Override
    public void updateUser(LudopiaUser user) {
        userRepo.save(user);
    }

    @Override
    public boolean addToUserList(int gameId) {
        LudopiaUser ludopiaUser = getCurrentUser();
        if(ludopiaUser != null){
            if(ludopiaUser.getGameList() == -1){
                ludopiaUser.setGameList(listService.createList(new GameList(ludopiaUser.getId(), OwnerType.USER, "","")).getId());
                this.updateUser(ludopiaUser);
            }
            try{
                listService.addGameToList(ludopiaUser.getGameList(),gameId);
                return true;
            } catch (GameAlreadyInListException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeFromUserList(int gameId) {
        LudopiaUser ludopiaUser = getCurrentUser();
        if(ludopiaUser != null && ludopiaUser.getGameList() != -1) {
            listService.removeGameToList(ludopiaUser.getGameList(), gameId);
            return true;
        }
        return false;
    }
}
