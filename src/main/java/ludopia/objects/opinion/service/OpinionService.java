package ludopia.objects.opinion.service;

import ludopia.objects.opinion.Opinion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OpinionService {
    List<Opinion> getAllOpinionFromUser(int userId);
    List<Opinion> getAllOpinionFromGame(int gameId);
    Opinion createOpinion(int userId, int gameId, int note, String message);
    boolean IsUserAlreadyHaveAnOpinionOnGame(int userId, int gameId);
}
