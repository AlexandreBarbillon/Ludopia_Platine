package ludopia.objects.associations.service;

import ludopia.objects.associations.Association;
import ludopia.objects.associations.repository.AssociationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserServiceImpl control the game creation/recover/update/deleting
 */
@Service
public class AssociationServiceImpl implements AssociationService {
    private AssociationRepository associationRepo;

    public AssociationServiceImpl(AssociationRepository associationRepo) {
        this.associationRepo = associationRepo;
    }

    @Override
    public Association createAssociation(Association association) {
        return associationRepo.save(association);
    }

    @Override
    public Association getAssoById(int id) {
        return associationRepo.findById(id).orElse(null);
    }

    @Override
    public Iterable<Association> getAll() {
        return associationRepo.findAll();
    }

    @Override
    public void removeAssoc(int id) {
        associationRepo.deleteById(id);
    }
}
