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
     * Prend l'id d'une GameList puis permet de créer une liste de Game à partir de son contenu
     * @param listId l'ID d'une liste
     * @return la liste des objet Game correspondant aux id contenu de la liste
     */
    List<Game> unwrapGameList(int listId);

    int getAverageNoteFromGame(int gameId);
}
