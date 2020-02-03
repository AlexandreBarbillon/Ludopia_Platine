package ludopia.objects.games.service;

import ludopia.objects.games.Game;

import java.util.List;

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

    /**
     * Search a game by a substring
     * @param search the substring of a game
     * @return the list of game found with the key word
     */
    List<Game> searchGame(String search);

    /**
     * go search a list by its id and transform all the games inside into Game
     * @param listId the id of the list
     * @return a list of Game
     */
    List<Game> unwrapGameList(int listId);

    int getAverageNoteFromGame(int gameId);
}
