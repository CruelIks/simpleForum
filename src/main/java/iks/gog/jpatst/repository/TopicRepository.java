package iks.gog.jpatst.repository;

import iks.gog.jpatst.model.Topic;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends PagingAndSortingRepository<Topic, Long> {

    /*@Query("Select t from Topic t order by t.createdWhen DESC")
    List<Topic> findAllOrdered();*/

}
