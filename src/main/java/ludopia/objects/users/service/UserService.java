package ludopia.objects.users.service;

import ludopia.objects.users.CredentialUser;
import ludopia.objects.users.LudopiaUser;

public interface UserService {
    /**
     * Retourne la liste de tous les utilisateurs
     * @return un Iterable de LudopiaUser
     */
    Iterable<LudopiaUser> getAllUsers();

    /**
     * retrouve un LudopiaUser par son identifiant
     * @param userId l'identiant d'un utilisateur
     * @return un LudopiaUser
     */
    LudopiaUser getUserById(int userId);
    /**
     * retrouve un LudopiaUser par son nom d'utilisateur
     * @param username le nom d'un utilisateur
     * @return un LudopiaUser
     */
    LudopiaUser getUserByUsername(String username);

    /**
     * Crée un utilisateur Ludopia
     * @param ludopiaUser l'utilisateur de Ludopia
     * @param password son mot de passe
     * @return un Ludopia User
     */
    LudopiaUser createUser(LudopiaUser ludopiaUser, String password);

    /**
     * Retourne les données de connexion d'un utilisateur depuis son nom
     * @param username le nom de l'utilisateur
     * @return un CredentialUser
     */
    CredentialUser getAuthentificationUserByUsername(String username);
    void removeUser(int id);

    /**
     * Retourne l'utilisateur courant
     * @return le LudopiaUser actuel
     */
    LudopiaUser getCurrentUser();
    void updateUser(LudopiaUser user);
}
