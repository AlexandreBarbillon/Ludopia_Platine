package ludopia.objects.associations.service;

import ludopia.objects.associations.Association;
import ludopia.objects.associations.repository.AssociationRepository;
import org.springframework.stereotype.Service;

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
}
