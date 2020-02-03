package ludopia.objects.opinion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
/**
 * An opinion is what somebody think about a game
 */
public class Opinion {
    @Id @GeneratedValue
    private int id;
    @Column
    private int gameId;
    @Column
    private int userId;
    @Column
    private int note;
    @Column
    private String message;

    public Opinion(){

    }

    public Opinion( int userId, int gameId, int note, String message) {
        this.gameId = gameId;
        this.userId = userId;
        this.note = note;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
