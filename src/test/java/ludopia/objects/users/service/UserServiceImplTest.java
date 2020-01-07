package ludopia.objects.users.service;

import ludopia.objects.users.User;
import ludopia.objects.users.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserRepository userRepo;
    UserServiceImpl userService;
    @Before
    public void before(){

    }

    @BeforeEach
    public void beforeTest(){
        userService = new UserServiceImpl(userRepo);
        userRepo.deleteAll();
    }
    @Test
    public void testTheUserCreation(){
        User paul = new User("paul","timoléon");
        userService.createUser(paul);
        User databasePaul = userService.getUserById(paul.getId());
        assertEquals(paul,databasePaul);
    }
    @Test
    public void testTheGetUserWithANonExistingUser(){
        User databasePaul = userService.getUserById(15);
        assertNull(databasePaul);
    }
    @Test
    void findByUsernameTest(){
        User paul = new User("paul","timoléon");
        userService.createUser(paul);
        User databasePaul = userService.getUserByUsername("paul");
        assertEquals(paul,databasePaul);
    }
}
