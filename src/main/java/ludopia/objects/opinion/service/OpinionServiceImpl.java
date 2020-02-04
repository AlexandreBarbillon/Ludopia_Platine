package ludopia.objects.opinion.service;

import ludopia.objects.opinion.Opinion;
import ludopia.objects.opinion.repository.OpinionRepository;
import ludopia.objects.users.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionServiceImpl implements OpinionService {
    private OpinionRepository opinionRepo;

    public OpinionServiceImpl(OpinionRepository opinionRepo) {
        this.opinionRepo = opinionRepo;
    }

    @Override
    public List<Opinion> getAllOpinionFromUser(int userId) {
        return opinionRepo.getAllByUserId(userId);
    }

    @Override
    public List<Opinion> getAllOpinionFromGame(int gameId) {
        return opinionRepo.getAllByGameId(gameId);
    }

    @Override
    public Opinion createOpinion(int userId, int gameId, int note, String message) {
        Opinion opinion = opinionRepo.findOpinionByUserIdAndGameId(userId,gameId).orElse(null);
        if(opinion != null){
            opinion.setNote(note);
            opinion.setMessage(message);
            return opinionRepo.save(opinion);
        }
        else{
            opinion = new Opinion(userId,gameId,note,message);
            return opinionRepo.save(opinion);
        }
    }

    @Override
    public boolean IsUserAlreadyHaveAnOpinionOnGame(int userId, int gameId) {
        Opinion opinion = opinionRepo.findOpinionByUserIdAndGameId(userId,gameId).orElse(null);
        return opinion!=null;
    }
}
