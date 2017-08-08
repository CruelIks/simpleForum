package iks.gog.jpatst.forms;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class MessageForm {
    @NotNull
    @Size(min = 1, max = 250)
    private String description;

    private Long topicId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "MessageForm{" +
                "description='" + description + '\'' +
                '}';
    }
}
