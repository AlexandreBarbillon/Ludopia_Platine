package ludopia.objects.users.service;

import ludopia.objects.users.User;

public interface UserService {
    Iterable<User> getAllUsers();
    User getUserById(int userId);
    User getUserByUsername(String username);
    User createUser(User user);
    void removeUser(String username);
}
