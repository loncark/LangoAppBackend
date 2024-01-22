package com.loncark.langoapp.repository;

import com.loncark.langoapp.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAll();

    Optional<Message> findById(Long id);

    void deleteById(Long id);

    Message save(Message message);

    List<Message> findBySenderIdAndReceiverId(Long senderId, Long receiverId);

    void deleteBySenderIdEqualsOrReceiverIdEquals(Long senderId, Long receiverId);
}
