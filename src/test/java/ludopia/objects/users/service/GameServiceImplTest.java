package ludopia.objects.users.service;

import ludopia.objects.games.Game;
import ludopia.objects.games.repository.GameRepository;
import ludopia.objects.games.service.GameServiceImpl;
import ludopia.objects.users.LudopiaUser;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class GameServiceImplTest {

    @Autowired
    GameRepository gameRepo;
    GameServiceImpl gameService;
    @Before
    public void before(){

    }

    @BeforeEach
    public void beforeTest(){
        gameService = new GameServiceImpl(gameRepo);
        gameRepo.deleteAll();
    }
    @Test
    public void testTheGameCreation(){
        Game game1 = new Game();
        game1.setName("game1");
        //Game game2 = new Game();
        //game2.setName("game2");
        gameService.createGame(game1);
        System.out.println(game1.getAddDate());
        Game result = gameService.getGameById(game1.getId());
    }

    @Test
    public void testFindingAllGamesSortByAddDate() throws InterruptedException {
        Game game1 = new Game();
        game1.setName("game1");
        Thread.sleep(1000);
        Game game2 = new Game();
        game2.setName("game2");
        gameService.createGame(game1);
        gameService.createGame(game2);
        var gameList = gameService.getGamesSortByDate(2);
        assertNotNull(gameList);
        assertEquals(2,gameList.size());
        assertEquals(game2,gameList.get(0));
        assertEquals(game1,gameList.get(1));
    }
}