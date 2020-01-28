package ludopia.objects.games.service;

import ludopia.objects.games.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
    /**
     * Recover games sorted by add date
     * @param amount the number or result to recover
     * @return a list of Game sort by add date
     */
    List<Game> getGamesSortByDate(int amount);
    List<Game> getGamesSortByDate();
    /**
     * Add a game to database
     * @param game the game to be added
     * @return the game added
     */
    Game createGame(Game game);

    /**
     * Return a game by the id entered
     * @param id the id of the game
     * @return the game if finded, null otherwise
     */
    Game getGameById(int id);
    List<Game> unwrapGameList(int listId);
}
