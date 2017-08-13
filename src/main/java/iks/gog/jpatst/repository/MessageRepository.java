package iks.gog.jpatst.repository;

import iks.gog.jpatst.model.Message;
import iks.gog.jpatst.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {


    Page<Message> findBytopic_id(Long topicId, Pageable pageable);

}
