package iks.gog.jpatst.repository;

import iks.gog.jpatst.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

    Page<Message> findBytopic_id(Long topicId, Pageable pageable);

}
