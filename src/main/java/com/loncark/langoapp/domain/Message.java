package com.loncark.langoapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "sender_id")
    Long senderId;
    @Column(name = "receiver_id")
    Long receiverId;
    @Column(name = "date_sent")
    LocalDateTime dateSent;
    @Column(name = "message_text")
    String messageText;
}
