package ludopia.objects.games.service;

import ludopia.objects.games.Game;
import ludopia.objects.games.repository.GameRepository;
import ludopia.objects.list.GameList;
import ludopia.objects.list.service.ListService;
import ludopia.objects.opinion.Opinion;
import ludopia.objects.opinion.service.OpinionService;
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
    @Autowired
    private OpinionService opinionService;

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
        return gameRepo.searchGames("%"+search.toUpperCase()+"%");
    }

    @Override
    public List<Game> unwrapGameList(int listId) {
        GameList gameList = listService.getListById(listId);
        List<Integer> gameIdList;
        if (gameList != null) {
            gameIdList = gameList.getGames();
        } else {
            gameIdList = new ArrayList<>();
        }
        ArrayList<Game> result = new ArrayList<>();
        for (Integer gameId:gameIdList) {
            Game game = getGameById(gameId);
            if(game != null){
                result.add(game);
            }
        }
        return result;
    }

    @Override
    public int getAverageNoteFromGame(int gameId) {
        List<Opinion> opinions = opinionService.getAllOpinionFromGame(gameId);
        int average = 0;
        for (Opinion opinion: opinions) {
            average+=opinion.getNote();
        }
        return average / opinions.size();
    }


}
