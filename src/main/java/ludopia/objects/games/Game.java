package ludopia.objects.games;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Un objet game contient :
 *  un identifiant,
 *  un nom,
 *  une description,
 *  un lien vers une image représentant le jeu
 *  si il s'agit d'une extension (non utilisé)
 *  la date de création du jeu
 *  la liste des extensions de ce jeu (non utilisé)
 */
@Entity
public class Game {
    @Id @GeneratedValue
    int id;
    @Column
    String name;
    @Column
    String description;
    @Column
    String imageLink;
    @Column
    boolean isExtension;
    @Temporal(TemporalType.TIMESTAMP)
    Date addDate;
    @ElementCollection
    List<Integer> extensionsId;

    public Game() {
        name = "";
        description = "";
        imageLink = "";
        extensionsId = new ArrayList<>();
        addDate = new Date();
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setIsExtension(boolean isExtension) { this.isExtension = isExtension;}

    public boolean isExtension() { return this.isExtension;}

    public List<Integer> getExtensionsId() {
        return extensionsId;
    }

    public void setExtensionsId(List<Integer> extensionsId) {
        this.extensionsId = extensionsId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        else return obj.getClass() == this.getClass() && ((Game) obj).getId() == this.getId();
    }

    @Override
    public String toString() {
        return "["+this.getId()+"] "+this.getName();
    }
}
