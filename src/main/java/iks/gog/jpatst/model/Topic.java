package iks.gog.jpatst.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "created_when")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdWhen;

    @Column(name = "updated_when")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedWhen;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedWhen() {
        return createdWhen;
    }

    public void setCreatedWhen(Date createdWhen) {
        this.createdWhen = createdWhen;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Date getUpdatedWhen() {
        return updatedWhen;
    }

    public void setUpdatedWhen(Date updatedWhen) {
        this.updatedWhen = updatedWhen;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", createdWhen=" + createdWhen +
                ", updatedWhenWhen=" + updatedWhen +
                ", messages=" + messages +
                ", user=" + user +
                '}';
    }
}
