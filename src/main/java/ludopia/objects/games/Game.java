package ludopia.objects.games;

import javax.persistence.*;
import java.util.List;

@Entity
public class Game {
    @Id @GeneratedValue
    long id;
    @Column
    String name;
    @Column
    String description;
    @Column
    String image_link;
    @Column
    boolean isExtension;
    @ElementCollection
    List<Integer> extensionsId;

    public Game(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public void setIsExtension(boolean isExtension) { this.isExtension = isExtension;};

    public boolean isExtension() { return this.isExtension;}

    public List<Integer> getExtensionsId() {
        return extensionsId;
    }

    public void setExtensionsId(List<Integer> extensionsId) {
        this.extensionsId = extensionsId;
    }
}
