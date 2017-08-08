package iks.gog.jpatst.repository;

import iks.gog.jpatst.model.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {

    @Query("Select t from Topic t order by t.createdWhen DESC")
    List<Topic> findAllOrdered();

}
