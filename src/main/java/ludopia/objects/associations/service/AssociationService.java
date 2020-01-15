package ludopia.objects.associations.service;

import ludopia.objects.associations.Association;
import ludopia.objects.games.Game;

import java.util.List;

public interface AssociationService {
    Association createAssociation(Association association);
    Association getAssoById(int id);

    Iterable<Association> getAll();
}
