package ludopia.objects.associations.service;

import ludopia.objects.associations.Association;
import java.util.List;

public interface AssociationService {
    Association createAssociation(Association association);
    Association getAssoById(int id);

    Iterable<Association> getAll();
    List<Association> findAssoHavingTheGame(int gameId);
    List<Association> findAssoFromUser(int userId);
}
