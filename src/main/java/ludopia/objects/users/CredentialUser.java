package ludopia.objects.users;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class CredentialUser extends User {
    @Id
    @Column
    private int userId;
    @Column
    private String username;
    @Column
    private String password;
    @ElementCollection
    private Collection<SimpleGrantedAuthority> authorities;
    public CredentialUser(String username, String password, Collection<SimpleGrantedAuthority> authorities, int userId) {
        super(username, password, authorities);
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public CredentialUser(){
        this("user","user", new ArrayList<>(),0);
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

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
