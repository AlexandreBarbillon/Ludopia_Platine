package ludopia.objects.users;


import ludopia.config.SecurityConfig;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A User is the account of somebody connected to Ludopia. For now, there is only the username and the password stored
 */
@Entity
public class LudopiaUser implements Serializable {
    @Id @GeneratedValue
    private int id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String description;
    @ElementCollection
    private Set<Integer> lists;
    @ElementCollection
    private Set<Integer> associations;
    @ElementCollection
    private Set<Integer> friends;
    /**
     * Construct the User object
     * @param username the username of the user
     * @param password his password
     */
    public LudopiaUser(String username, String password) {
        this.username = username;
        this.setPassword(password);
    }

    public LudopiaUser(){
        this.lists = new HashSet<>();
        this.associations = new HashSet<>();
        this.friends = new HashSet<>();
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
        this.password = SecurityConfig.getPasswordEncoder().encode(password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Integer> getLists() {
        return lists;
    }

    public void addList(int list) {
        this.lists.add(list);
    }

    public void removeList(int list) {
        this.lists.remove(list);
    }

    public Set<Integer> getAssociations() {
        return associations;
    }

    public void addAssociation(int association) {
        this.associations.add(association);
    }

    public void removeAssociation(int association) {
        this.associations.remove(association);
    }

    public Set<Integer> getFriends() {
        return friends;
    }

    public void addFriend(int friend) {
        this.friends.add(friend);
    }
    public void removeFriend(int friend) {
        this.friends.remove(friend);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!obj.getClass().equals(this.getClass())) return false;
        LudopiaUser otherLudopiaUser = (LudopiaUser) obj;
        return otherLudopiaUser.id == this.id;
    }

    @Override
    public String toString() {
        return this.username+" "+this.password;
    }
}
