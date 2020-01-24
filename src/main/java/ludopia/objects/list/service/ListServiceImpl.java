package ludopia.objects.list.service;

import ludopia.objects.list.GameList;
import ludopia.objects.list.OwnerType;
import ludopia.objects.list.exceptions.GameAlreadyInListException;
import ludopia.objects.list.repository.ListRepository;
import ludopia.objects.users.LudopiaUser;
import ludopia.objects.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UserServiceImpl control the user creation/recover/update/deleting
 */
@Service
public class ListServiceImpl implements ListService {
    private ListRepository listRepo;

    public ListServiceImpl(ListRepository listRepo) {
        this.listRepo = listRepo;
    }

    @Override
    public Iterable<GameList> findAllListsFromAnUser(int userId) {
        return listRepo.findByIdOwnerAndOwnerType(userId, OwnerType.USER);
    }

    @Override
    public Iterable<GameList> findAllListsFromAnAssociation(int assoId) {
        return listRepo.findByIdOwnerAndOwnerType(assoId, OwnerType.ASSO);
    }

    @Override
    public GameList getListById(int listId) {
        return listRepo.findById(listId).orElse(null);
    }

    @Override
    public boolean addGameToList(int listId, int gameId) throws GameAlreadyInListException {
        GameList gameList = this.getListById(listId);
        if(gameList != null){
            gameList.addGameToList(gameId);
            listRepo.save(gameList);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean removeGameToList(int listId, int gameId) {
        GameList gameList = this.getListById(listId);
        if(gameList != null){
            gameList.removeGameFromList(gameId);
            listRepo.save(gameList);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public GameList createList(GameList gameList) {
        return this.listRepo.save(gameList);
    }
}
