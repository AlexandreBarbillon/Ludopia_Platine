package ludopia.objects.list.service;

import ludopia.objects.list.GameList;
import ludopia.objects.list.exceptions.GameAlreadyInListException;

public interface ListService {
    /**
     * Récupère toutes les listes d'un utilisateur
     * @param userId l'id de l'utilisateur
     * @return un Iterable de GameList
     */
    Iterable<GameList> findAllListsFromAnUser(int userId);

    /**
     * Récupère toutes les listes d'une association
     * @param assoId l'id d'une Association
     * @return un Iterable de GameList
     */
    Iterable<GameList> findAllListsFromAnAssociation(int assoId);

    /**
     * Récupère une liste par son id
     * @param listId l'id de la liste
     * @return une liste de jeux
     */
    GameList getListById(int listId);

    /**
     * Ajoute l'id d'un jeu a une liste
     * @param listId
     * @param gameId
     * @return
     * @throws GameAlreadyInListException
     */
    boolean addGameToList(int listId,int gameId) throws GameAlreadyInListException;
    boolean removeGameToList(int listId, int gameId);
    GameList createList(GameList gameList);
}
