package ludopia.objects.users.service;

import ludopia.objects.associations.Association;
import ludopia.objects.associations.repository.AssociationRepository;
import ludopia.objects.associations.service.AssociationServiceImpl;
import ludopia.objects.games.Game;
import ludopia.objects.games.repository.GameRepository;
import ludopia.objects.games.service.GameServiceImpl;
import ludopia.objects.list.GameList;
import ludopia.objects.list.exceptions.GameAlreadyInListException;
import ludopia.objects.list.repository.ListRepository;
import ludopia.objects.list.service.ListService;
import ludopia.objects.list.service.ListServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@SpringBootTest
public class AssociationServiceImplTest {

    @Autowired
    AssociationRepository assoRepo;
    @Autowired
    ListRepository listRepo;
    ListService listService;
    AssociationServiceImpl assoService;
    @Before
    public void before(){

    }

    @BeforeEach
    public void beforeTest(){
        listService = new ListServiceImpl(listRepo);
        assoService = new AssociationServiceImpl(assoRepo,listService);
        listRepo.deleteAll();
        assoRepo.deleteAll();
    }
    @Test
    @Transactional
    public void testTheAssociationRecoveringByGameInDatabase() throws GameAlreadyInListException {
        Association asso1 = new Association();
        Association asso2 = new Association();
        Association asso3 = new Association();

        assoService.createAssociation(asso1);
        GameList assoList = listService.getListById(asso1.getPossessedGamesList());
        assoList.addGameToList(15);
        assoService.createAssociation(asso2);
        assoList = listService.getListById(asso2.getPossessedGamesList());
        assoList.addGameToList(15);
        assoService.createAssociation(asso3);
        assoList = listService.getListById(asso3.getPossessedGamesList());
        assoList.addGameToList(12);
        List<Association> result = assoService.findAssoHavingTheGame(15);
        assertEquals(2,result.size());
        assertTrue(result.contains(asso1));
        assertTrue(result.contains(asso2));
    }

    @Test
    @Transactional
    public void testTheAssociationRecoveringByGameInDatabaseWithoutGames() throws GameAlreadyInListException {
        Association asso1 = new Association();
        Association asso2 = new Association();
        Association asso3 = new Association();

        assoService.createAssociation(asso1);
        GameList assoList = listService.getListById(asso1.getPossessedGamesList());
        assoList.addGameToList(15);
        assoService.createAssociation(asso2);
        assoList = listService.getListById(asso2.getPossessedGamesList());
        assoList.addGameToList(15);
        assoService.createAssociation(asso3);
        assoList = listService.getListById(asso3.getPossessedGamesList());
        assoList.addGameToList(12);
        List<Association> result = assoService.findAssoHavingTheGame(10);
        assertEquals(0,result.size());
    }

}
