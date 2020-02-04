package ludopia.objects.users.service;

import ludopia.objects.users.CredentialUser;
import ludopia.objects.users.LudopiaUser;
import ludopia.objects.users.repository.CredentialUserRepository;
import ludopia.objects.users.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserRepository userRepo;
    @Autowired
    CredentialUserRepository credUserRepo;
    UserServiceImpl userService;
    @Before
    public void before(){

    }

    @BeforeEach
    public void beforeTest(){
        userService = new UserServiceImpl(userRepo,credUserRepo);
        userRepo.deleteAll();
    }
    @Test
    public void testTheUserCreation(){
        LudopiaUser paul = new LudopiaUser("paul","timoléon");
        userService.createUser(paul,paul.getPassword());
        LudopiaUser databasePaul = userService.getUserById(paul.getId());
        assertEquals(paul,databasePaul);
    }
    @Test
    public void testTheGetUserWithANonExistingUser(){
        LudopiaUser databasePaul = userService.getUserById(15);
        assertNull(databasePaul);
    }
    @Test
    void findByUsernameTest(){
        LudopiaUser paul = new LudopiaUser("paul","timoléon");
        userService.createUser(paul,paul.getPassword());
        LudopiaUser databasePaul = userService.getUserByUsername("paul");
        assertEquals(paul,databasePaul);
    }
}
