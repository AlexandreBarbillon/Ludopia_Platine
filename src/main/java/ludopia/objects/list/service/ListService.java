package ludopia.objects.list.service;

import ludopia.objects.list.GameList;
import ludopia.objects.list.exceptions.GameAlreadyInListException;

public interface ListService {
    Iterable<GameList> findAllListsFromAnUser(int userId);
    Iterable<GameList> findAllListsFromAnAssociation(int assoId);
    GameList getListById(int listId);
    boolean addGameToList(int listId,int gameId) throws GameAlreadyInListException;
    boolean removeGameToList(int listId, int gameId);
    GameList createList(GameList gameList);
}
