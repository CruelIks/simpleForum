package iks.gog.jpatst;

import iks.gog.jpatst.model.Topic;
import iks.gog.jpatst.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Date;

@SpringBootApplication
public class JpatstApplication {

    @PostConstruct
    public void showTopics() {

        /*Topic newTopic = new Topic();
        newTopic.setCreatedWhen(new Date());
        newTopic.setDescription("JPA created topic");
        topicRepository.save(newTopic);

        Topic oldTopic = topicRepository.findOne(1L);
        if (oldTopic != null){
            oldTopic.setDescription("corrected " + oldTopic.getDescription());
            topicRepository.save(oldTopic);
        }

        for (Topic topic : topicRepository.findAll()) {
            System.out.println(topic);
        }*/

    }

    public static void main(String[] args) {
        SpringApplication.run(JpatstApplication.class, args);
    }
}
