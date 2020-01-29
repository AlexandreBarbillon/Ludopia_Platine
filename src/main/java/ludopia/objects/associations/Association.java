package ludopia.objects.associations;

import ludopia.objects.games.Game;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Association {
    @Id @GeneratedValue @NonNull
    int id;
    @Column @NonNull
    String name;
    @Column
    String description;
    @Column @NonNull
    double latitude;
    @Column @NonNull
    double longitude;
    @Column
    String imageLink;
    @ElementCollection
    List<Integer> members;
    @Column @NonNull
    int possessedGamesList;
    @ElementCollection
    List<Integer> lists;
    @Column @NonNull
    int admin;

    public Association(){
        possessedGamesList = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }


    public int getPossessedGamesList() {
        return possessedGamesList;
    }

    public void setPossessedGamesList(int possessedGamesList) {
        this.possessedGamesList = possessedGamesList;
    }
    @Override
    public String toString() {
        return this.getName()+" id="+this.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        else return obj.getClass() == this.getClass() && ((Association) obj).getId() == this.getId();
    }
}
