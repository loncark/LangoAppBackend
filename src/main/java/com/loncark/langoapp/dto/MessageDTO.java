package com.loncark.langoapp.dto;

import com.loncark.langoapp.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    Long id;
    Long senderId;
    Long receiverId;
    LocalDateTime dateSent;
    String messageText;

    public MessageDTO(Message m) {
        this.dateSent = m.getDateSent();
        this.id = m.getId();
        this.receiverId = m.getReceiverId();
        this.senderId = m.getSenderId();
        this.messageText = m.getMessageText();
    }
}
