package ludopia.objects.associations;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

/**
 * Une association possède :
 *  un identifiant,
 *  un nom,
 *  une description,
 *  une position(latitude,longitude),
 *  un lien vers un avatar,
 *  une liste de membre (non utilisé)
 *  une liste principale des jeux qu'ils possèdent
 *  la liste des listes de jeux (non utilisé)
 *  l'id du créateur de l'association
 */
@Entity
public class Association {
    @Id @GeneratedValue @NonNull
    int id;
    @Column @NonNull
    private
    String name;
    @Column(length=2000)
    private
    String description;
    @Column
    private
    String address;
    @Column @NonNull
    private
    double latitude;
    @Column @NonNull
    private
    double longitude;
    @Column
    private
    String imageLink;
    @ElementCollection
    private
    List<Integer> members;
    @Column @NonNull
    private
    int possessedGamesList;
    @ElementCollection
    private
    List<Integer> lists;
    @Column @NonNull
    private
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
