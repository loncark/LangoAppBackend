package com.loncark.langoapp.service;

import com.loncark.langoapp.domain.Message;
import com.loncark.langoapp.dto.MessageDTO;
import com.loncark.langoapp.dto.UserDTO;
import com.loncark.langoapp.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MessageDTO> findAll() {
        return messageRepository.findAll().stream().map(MessageDTO::new).collect(Collectors.toList());
    }

    @Override
    public Optional<MessageDTO> findById(Long id) {
        return messageRepository.findById(id).map(message -> new MessageDTO(message));
    }

    @Override
    public Optional<MessageDTO> save(Message message) {
        Optional<Message> savedMessage = Optional.of(messageRepository.save(message));

        if(savedMessage.isPresent()) {
            return Optional.of(new MessageDTO(savedMessage.get()));
        }
        else return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public List<MessageDTO> findBySenderIdAndReceiverId(Long senderId, Long receiverId) {
        return messageRepository.findBySenderIdAndReceiverId(senderId, receiverId).stream().map(MessageDTO::new).collect(Collectors.toList());
    }
}
