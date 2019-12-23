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
@PropertySource("test.properties")
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserRepository userRepo;

    @BeforeEach
    public void beforeTest(){
        userRepo.deleteAll();
    }
    @Test
    public void testTheUserCreation(){
        User paul = new User("paul","timoléon");
        UserServiceImpl userService = new UserServiceImpl(userRepo);
        userService.createUser(paul);
        User databasePaul = userService.getUser("paul");
        assertEquals(paul,databasePaul);
    }
    @Test
    public void testTheGetUserWithANonExistingUser(){
        UserServiceImpl userService = new UserServiceImpl(userRepo);
        User databasePaul = userService.getUser("paul");
        assertNull(databasePaul);
    }
}
