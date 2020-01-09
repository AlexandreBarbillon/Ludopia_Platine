package ludopia.objects.games;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Game {
    @Id @GeneratedValue
    int id;
    @Column
    String name;
    @Column
    String description;
    @Column
    String image_link;
    @Column
    boolean isExtension;
    @Temporal(TemporalType.TIMESTAMP)
    Date addDate;
    @ElementCollection
    List<Integer> extensionsId;

    public Game() {
        name = "";
        description = "";
        image_link = "";
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

    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass() && ((Game) obj).getId() == this.getId();
    }
}
