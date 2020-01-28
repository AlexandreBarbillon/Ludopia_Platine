package ludopia.objects.associations.service;

import ludopia.objects.associations.Association;
import ludopia.objects.associations.repository.AssociationRepository;
import ludopia.objects.list.GameList;
import ludopia.objects.list.OwnerType;
import ludopia.objects.list.repository.ListRepository;
import ludopia.objects.list.service.ListService;
import ludopia.objects.users.CredentialUser;
import ludopia.objects.users.LudopiaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserServiceImpl control the game creation/recover/update/deleting
 */
@Service
public class AssociationServiceImpl implements AssociationService {
    private AssociationRepository associationRepo;
    private ListService listService;
    public AssociationServiceImpl(AssociationRepository associationRepo, ListService listService) {
        this.associationRepo = associationRepo;
        this.listService = listService;
    }

    @Override
    public Association createAssociation(Association association) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object user = authentication.getPrincipal();
        if(user instanceof CredentialUser){
            CredentialUser credentialUser = (CredentialUser) user;
            association.setAdmin(credentialUser.getUserId());
            GameList assoList = new GameList(association.getId(), OwnerType.ASSO, "Les jeux en stock", "");
            assoList = listService.createList(assoList);
            association.setPossessedGamesList(assoList.getId());
            return associationRepo.save(association);
        }
        return null;
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
    public List<Association> findAssoHavingTheGame(int gameId) {
        return associationRepo.findAssoHavingTheGame(gameId);
    }

    @Override
    public List<Association> findAssoFromUser(int userId) {
        return associationRepo.findAssociationsByAdmin(userId);
    }
}
