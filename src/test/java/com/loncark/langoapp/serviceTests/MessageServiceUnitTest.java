package com.loncark.langoapp.serviceTests;

import com.loncark.langoapp.MockDataTest;
import com.loncark.langoapp.domain.Message;
import com.loncark.langoapp.dto.MessageDTO;
import com.loncark.langoapp.repository.MessageRepository;
import com.loncark.langoapp.service.MessageService;
import com.loncark.langoapp.service.MessageServiceImpl;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MessageServiceUnitTest extends MockDataTest {

    private final MessageRepository messageRepository = EasyMock.createMock(MessageRepository.class);
    private final MessageService messageService = new MessageServiceImpl(messageRepository);

    @Test
    public void GivenValidId_WhenFindById_ThenReturnMessageDTO() {
        Long messageId = 1L;
        Optional<Message> mockMessage = Optional.of(mockMessages.get(0));
        expect(messageRepository.findById(messageId)).andReturn(mockMessage);
        replay(messageRepository);

        Optional<MessageDTO> actualResult = messageService.findById(messageId);

        Optional<MessageDTO> expectedResult = Optional.of(mockMessageDTOs.get(0));

        assertEquals(actualResult, expectedResult);
        verify(messageRepository);
    }

    @Test
    public void GivenValidMessage_WhenSave_ThenReturnSavedMessageDTO() {
        Message message = new Message(null, 3L, 7L, LocalDateTime.now(), "This is a new message");
        Message savedMessage = new Message(11L, 3L, 7L, LocalDateTime.now(), "This is a new message");
        expect(messageRepository.save(message)).andReturn(savedMessage);
        replay(messageRepository);

        Optional<MessageDTO> actualResult = messageService.save(message);

        Optional<MessageDTO> expectedResult = Optional.of(new MessageDTO(savedMessage));

        assertEquals(actualResult.get(), expectedResult.get());
        verify(messageRepository);
    }

    @Test
    public void GivenValidId_WhenDeleteById_ThenRepositoryCalled() {
        Long messageId = 3L;
        messageRepository.deleteById(messageId);
        expectLastCall().once();
        replay(messageRepository);

        messageService.deleteById(messageId);

        verify(messageRepository);
    }

    @Test
    public void GivenValidSenderIdAndReceiverId_WhenFindBySenderIdAndReceiverId_ThenReturnMessageDTOList() {
        Long senderId = 6L;
        Long receiverId = 2L;

        expect(messageRepository.findBySenderIdAndReceiverId(senderId, receiverId)).andReturn(Arrays.asList(mockMessages.get(1), mockMessages.get(4)));
        replay(messageRepository);

        List<MessageDTO> actualResult = messageService.findBySenderIdAndReceiverId(senderId, receiverId);

        assertEquals(actualResult, Arrays.asList(mockMessageDTOs.get(1), mockMessageDTOs.get(4)));
        verify(messageRepository);
    }
}

