package iks.gog.jpatst.forms;


import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
public class TopicForm {
    @NotNull
    @Size(min = 2, max = 50)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TopicForm{" +
                "description='" + description + '\'' +
                '}';
    }
}
