package ludopia.objects.games.service;

import ludopia.objects.games.Game;
import ludopia.objects.games.repository.GameRepository;
import ludopia.objects.list.GameList;
import ludopia.objects.list.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UserServiceImpl control the game creation/recover/update/deleting
 */
@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepo;
    @Autowired
    private ListService listService;

    public GameServiceImpl(GameRepository gameRepo) {
        this.gameRepo = gameRepo;
    }

    @Override
    public List<Game> getGamesSortByDate(int amount) {
        return gameRepo.findByOrderByAddDateDesc(PageRequest.of(0,amount));
    }

    public List<Game> getGamesSortByDate() {
        return gameRepo.findByOrderByAddDateDesc(Pageable.unpaged());
    }

    @Override
    public Game createGame(Game game) {
        return gameRepo.save(game);
    }

    @Override
    public Game getGameById(int id) {
        return gameRepo.findById(id).orElse(null);
    }

    @Override
    public List<Game> searchGame(String search) {
        return gameRepo.searchGames("%"+search+"%");
    }

    @Override
    public List<Game> unwrapGameList(int listId) {
        GameList gameList = listService.getListById(listId);
        List<Integer> gameIdList = gameList.getGames();
        ArrayList<Game> result = new ArrayList<>();
        for (Integer gameId:gameIdList) {
            Game game = getGameById(gameId);
            if(game != null){
                result.add(game);
            }
        }
        return result;
    }


}
