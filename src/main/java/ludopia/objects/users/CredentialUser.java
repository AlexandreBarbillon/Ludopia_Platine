package ludopia.objects.users;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Collections;

/**
 * Les informations de connexion d'un utilisateur, il contient :
 *  un identifiant utilisateur
 *  un nom d'utilisateur
 *  un mot de passe
 */
@Entity
public class CredentialUser extends User {
    @Id
    @Column
    private int userId;
    @Column
    private String username;
    @Column
    private String password;
    public CredentialUser(String username, String password, int userId) {
        super(username, password, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public CredentialUser(){
        this("user","user",0);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
