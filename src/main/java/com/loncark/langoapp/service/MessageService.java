package com.loncark.langoapp.service;

import com.loncark.langoapp.domain.Message;
import com.loncark.langoapp.dto.MessageDTO;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    List<MessageDTO> findAll();

    Optional<MessageDTO> findById(Long id);

    Optional<MessageDTO> save(Message message);

    void deleteById(Long id);

    List<MessageDTO> findBySenderIdAndReceiverId(Long senderId, Long receiverId);

    void deleteMessagesTiedToUserWithId(Long id);
}
