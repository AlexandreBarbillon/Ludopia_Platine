package ludopia.objects.list;


import ludopia.objects.list.exceptions.GameAlreadyInListException;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A User is the account of somebody connected to Ludopia. For now, there is only the username and the password stored
 */
@Entity
public class GameList implements Serializable {
    @Id @GeneratedValue @NonNull
    private int id;
    @Column @NonNull
    private int idOwner;
    @Column @NonNull
    private OwnerType ownerType;
    @Column @NonNull
    private String title;
    @Column @NonNull
    private String description;
    @ElementCollection @NonNull
    private List<Integer> games;

    public GameList(){
        this.games = new ArrayList<>();
    }
    public GameList(int idOwner, OwnerType ownerType, String title, String description){
        this();
        this.idOwner = idOwner;
        this.ownerType = ownerType;
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<Integer> getGames() {
        return games;
    }



    public void addGameToList(int gameId) throws GameAlreadyInListException {
        if(this.games.contains(gameId)){
            throw new GameAlreadyInListException();
        }
        else{
            this.games.add(gameId);
        }
    }

    public void removeGameFromList(int gameId){
        this.games.remove(gameId);
    }

    @Override
    public String toString() {
        return this.getGames().toString();
    }
}
