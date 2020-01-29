package ludopia.objects.opinion.repository;

import ludopia.objects.opinion.Opinion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OpinionRepository extends CrudRepository<Opinion, String> {
    List<Opinion> getAllByGameId(int gameId);
    List<Opinion> getAllByUserId(int userId);
}
