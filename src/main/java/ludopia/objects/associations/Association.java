package ludopia.objects.associations;

import ludopia.objects.list.GameList;

import javax.persistence.*;
import java.util.List;

@Entity
public class Association {
    @Id @GeneratedValue
    int id;
    @Column
    String name;
    @Column
    String description;
    @Column
    double latitude;
    @Column
    double longitude;
    @Column
    String logo_img_link;
    @ElementCollection
    List<Integer> members;



    @Column
    int possessedGamesList;
    @ElementCollection
    List<Integer> lists;
    @Column
    int admin;
    public Association(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLogo_img_link() {
        return logo_img_link;
    }

    public void setLogo_img_link(String logo_img_link) {
        this.logo_img_link = logo_img_link;
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
}
