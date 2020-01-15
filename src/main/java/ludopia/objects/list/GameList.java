package ludopia.objects.list;


import ludopia.config.SecurityConfig;
import ludopia.objects.list.OwnerType;
import ludopia.objects.list.exceptions.GameAlreadyInListException;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A User is the account of somebody connected to Ludopia. For now, there is only the username and the password stored
 */
@Entity
public class GameList implements Serializable {
    @Id @GeneratedValue @NonNull
    int id;
    @Column @NonNull
    int idOwner;
    @Column @NonNull
    OwnerType ownerType;
    @Column @NonNull
    String title;
    @Column @NonNull
    String description;
    @ElementCollection @NonNull
    List<Integer> gameList;

    public GameList(){
        this.gameList = new ArrayList<>();
    }
    public GameList(int idOwner, OwnerType ownerType, String title, String description){
        this();
        this.idOwner = idOwner;
        this.ownerType = ownerType;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getGameList() {
        return gameList;
    }

    public void addGameToList(int gameId) throws GameAlreadyInListException {
        if(this.gameList.contains(gameId)){
            throw new GameAlreadyInListException();
        }
        else{
            this.gameList.add(gameId);
        }
    }

    public void removeGameFromList(int gameId){
        this.gameList.remove(gameId);
    }
}
