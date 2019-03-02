package backend.entity;

import backend.enums.MessageType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "tbl_message")
public class Message {
    @Id
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private String email;

    private String title;

    @Lob
    private String content;

    @Temporal(TemporalType.DATE)
    private Calendar releasedTime;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private MessageType type;

    private Boolean isRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Message() {
        setRead(false);
    }

    public Message(String email, String title, String content, MessageType type) {
        this.email = email;
        this.title = title;
        this.content = content;
        this.releasedTime = Calendar.getInstance();
        this.type = type;
        this.isRead = false;
    }
}
