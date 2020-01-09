package ludopia.objects.games.service;

import ludopia.objects.games.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
    List<Game> getGamesSortByDate(int amount);
    Game createGame(Game game);
    Game getGameById(int id);
}
