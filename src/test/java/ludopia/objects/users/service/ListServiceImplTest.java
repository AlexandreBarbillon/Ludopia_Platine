package ludopia.objects.users.service;

import ludopia.objects.list.GameList;
import ludopia.objects.list.OwnerType;
import ludopia.objects.list.exceptions.GameAlreadyInListException;
import ludopia.objects.list.repository.ListRepository;
import ludopia.objects.list.service.ListServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
public class ListServiceImplTest {

    @Autowired
    ListRepository listRepo;
    ListServiceImpl listService;
    @Before
    public void before(){

    }

    @BeforeEach
    public void beforeTest(){
        listService = new ListServiceImpl(listRepo);
        listRepo.deleteAll();
    }
    @Test
    @Transactional
    public void testTheListAddition() throws GameAlreadyInListException {
        GameList gameList = listService.createList(new GameList(1, OwnerType.USER, "my list", "it is my list"));
        assertTrue(gameList.getGames().isEmpty());
        listService.addGameToList(gameList.getId(), 5);
        GameList recoveredGameList = listService.getListById(gameList.getId());
        assertEquals(1,recoveredGameList.getGames().size());
        assertEquals(Integer.valueOf(5),recoveredGameList.getGames().get(0));
    }
}
