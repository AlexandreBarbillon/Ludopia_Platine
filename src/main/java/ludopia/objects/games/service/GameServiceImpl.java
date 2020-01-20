package ludopia.objects.games.service;

import ludopia.objects.games.Game;
import ludopia.objects.games.repository.GameRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * UserServiceImpl control the game creation/recover/update/deleting
 */
@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepo;

    public GameServiceImpl(GameRepository gameRepo) {
        this.gameRepo = gameRepo;
    }

    @Override
    public List<Game> getGamesSortByDate(int amount) {
        return gameRepo.findByOrderByAddDateDesc();
    }

    @Override
    public Game createGame(Game game) {
        return gameRepo.save(game);
    }

    @Override
    public Game getGameById(int id) {
        return gameRepo.findById(id).orElse(null);
    }
}
