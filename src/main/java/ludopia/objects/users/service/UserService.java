package ludopia.objects.users.service;

import ludopia.objects.users.CredentialUser;
import ludopia.objects.users.LudopiaUser;

public interface UserService {
    Iterable<LudopiaUser> getAllUsers();
    LudopiaUser getUserById(int userId);
    LudopiaUser getUserByUsername(String username);
    LudopiaUser createUser(LudopiaUser ludopiaUser, String password);
    CredentialUser getAuthentificationUserByUsername(String username);
    void removeUser(String username);
    LudopiaUser getCurrentUser();
}
