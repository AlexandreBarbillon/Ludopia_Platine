package ludopia.objects.users;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A User is the account of somebody connected to Ludopia. For now, there is only the username and the password stored
 */
@Entity
public class User {
    @Id
    String username;
    @Column
    String password;

    /**
     * Construct the User object
     * @param username the username of the user
     * @param password his password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!obj.getClass().equals(this.getClass())) return false;
        User otherUser = (User) obj;
        return otherUser.password.equals(this.password) && otherUser.username.equals(this.username);
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }
}
