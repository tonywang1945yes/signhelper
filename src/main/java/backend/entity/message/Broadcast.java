package backend.entity.message;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "tbl_broadcast")
public class Broadcast extends Message{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @Temporal(value = TemporalType.DATE)
    private Calendar releasedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getReleasedTime() {
        return releasedTime;
    }

    public void setReleasedTime(Calendar releasedTime) {
        this.releasedTime = releasedTime;
    }

    public Broadcast(String title, String content, Calendar releasedTime) {
        this.title = title;
        this.content = content;
        this.releasedTime = releasedTime;
    }

    public Broadcast() {
    }
}
