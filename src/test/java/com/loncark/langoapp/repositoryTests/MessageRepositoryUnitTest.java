package com.loncark.langoapp.repositoryTests;

import com.loncark.langoapp.MockDataTest;
import com.loncark.langoapp.domain.Message;
import com.loncark.langoapp.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MessageRepositoryUnitTest extends MockDataTest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void testFindAll() {
        List<Message> actualMessages = messageRepository.findAll();
        assertEquals(mockMessages, actualMessages);
    }

    @Test
    public void testFindById() {
        Optional<Message> actualMessage = messageRepository.findById(1L);
        assertEquals(Optional.of(mockMessages.get(0)), actualMessage);
    }

    @Test
    public void testDeleteById() {
        messageRepository.deleteById(2L);
        assertFalse(messageRepository.findById(2L).isPresent());
    }

    @Test
    public void testSave() {
        Message newMessage = new Message(11L, 3L, 7L, LocalDateTime.now(), "This is a new message");
        Message savedMessage = messageRepository.save(newMessage);
        assertEquals(newMessage, savedMessage);
    }

    @Test
    public void testFindBySenderIdAndReceiverId() {
        List<Message> actualMessages = messageRepository.findBySenderIdAndReceiverId(6L, 2L);
        assertEquals(2, actualMessages.size());
    }
}

