package ludopia.objects.games.repository;

import ludopia.objects.games.Game;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends CrudRepository<Game, String> {
    Optional<Game> findById(int id);
    List<Game>  findByOrderByAddDateDesc(Pageable pageable);
    @Query("FROM Game WHERE name LIKE %:search%")
    List<Game> searchGames(String search);
}
