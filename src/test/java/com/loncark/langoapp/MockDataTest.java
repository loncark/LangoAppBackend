package com.loncark.langoapp;

import com.loncark.langoapp.domain.Appointment;
import com.loncark.langoapp.domain.Message;
import com.loncark.langoapp.domain.Review;
import com.loncark.langoapp.domain.User;
import com.loncark.langoapp.dto.AppointmentDTO;
import com.loncark.langoapp.dto.MessageDTO;
import com.loncark.langoapp.dto.ReviewDTO;
import com.loncark.langoapp.dto.UserDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public abstract class MockDataTest {

    protected final List<User> mockUsers = Arrays.asList(
            new User(1L, "John", "$2a$10$GglJYDGHtabzwxbbeFfhle7A/W5z9OhHcPCj1RB2dUJyKItFW/d5K", "ROLE_ADMIN", "UK",
                    "Experienced administrator with a deep passion for languages. I believe in the power of effective communication to bring people together.", "ENGLISH,SPANISH"),
            new User(2L, "Marko", "$2a$10$OquQc.91.x9fjj0dvIjIseAQNZ2v1fxQ7MWqSqe.GS9iHxmarR5JC", "ROLE_USER", "CROATIA",
                    "A language enthusiast hailing from the beautiful country of Croatia. I find joy in exploring the intricacies of different languages and cultures.", "CROATIAN,ENGLISH"),
            new User(3L, "Ivan", "$2a$10$tFhJLvN30D5GDAB9Br25oe/sgFMAylhI7WG93yoSiFfH4j3kueNS2", "ROLE_USER", "RUSSIA",
                    "Greetings from Russia! As a multilingual individual, I enjoy the richness that each language brings to our understanding of the world.", "RUSSIAN,GERMAN"));

    protected final List<UserDTO> mockUserDTOs = Arrays.asList(
            new UserDTO(1L, "John", "$2a$10$GglJYDGHtabzwxbbeFfhle7A/W5z9OhHcPCj1RB2dUJyKItFW/d5K", "ROLE_ADMIN", "UK",
                    "Experienced administrator with a deep passion for languages. I believe in the power of effective communication to bring people together.", "ENGLISH,SPANISH"),
            new UserDTO(2L, "Marko", "$2a$10$OquQc.91.x9fjj0dvIjIseAQNZ2v1fxQ7MWqSqe.GS9iHxmarR5JC", "ROLE_USER", "CROATIA",
                    "A language enthusiast hailing from the beautiful country of Croatia. I find joy in exploring the intricacies of different languages and cultures.", "CROATIAN,ENGLISH"),
            new UserDTO(3L, "Ivan", "$2a$10$tFhJLvN30D5GDAB9Br25oe/sgFMAylhI7WG93yoSiFfH4j3kueNS2", "ROLE_USER", "RUSSIA",
                    "Greetings from Russia! As a multilingual individual, I enjoy the richness that each language brings to our understanding of the world.", "RUSSIAN,GERMAN"));

    protected final List<Appointment> mockAppointments = Arrays.asList(
            new Appointment(1L, 2L, 6L, LocalDate.parse("2023-05-06"), "We are gonna get to know each other."),
            new Appointment(2L, 3L, 7L, LocalDate.parse("2023-01-01"), "We are gonna talk some German."),
            new Appointment(3L, 6L, 8L, LocalDate.parse("2024-03-04"), "Online coffee is always a good idea."),
            new Appointment(4L, 3L, 6L, LocalDate.parse("2024-07-08"), "We have to practice our Russian before it gets stale."),
            new Appointment(5L, 4L, 7L, LocalDate.parse("2024-06-05"), "Non parliamo inglese pero italiano e molto buono.")
    );

    protected final List<AppointmentDTO> mockAppointmentDTOs = Arrays.asList(
            new AppointmentDTO(1L, 2L, 6L, LocalDate.parse("2023-05-06"), "We are gonna get to know each other."),
            new AppointmentDTO(2L, 3L, 7L, LocalDate.parse("2023-01-01"), "We are gonna talk some German."),
            new AppointmentDTO(3L, 6L, 8L, LocalDate.parse("2024-03-04"), "Online coffee is always a good idea."),
            new AppointmentDTO(4L, 3L, 6L, LocalDate.parse("2024-07-08"), "We have to practice our Russian before it gets stale."),
            new AppointmentDTO(5L, 4L, 7L, LocalDate.parse("2024-06-05"), "Non parliamo inglese pero italiano e molto buono.")
    );

    protected final List<Review> mockReviews = Arrays.asList(
            new Review(1L, 2L, 6L, LocalDate.parse("2022-02-06"), 5L, "Great guy. Wish I had met him sooner. Great speaker and teacher!"),
            new Review(2L, 3L, 7L, LocalDate.parse("2022-08-01"), 4L, "We got off of the wrong foot but the guy is still great. Would not recommend to complete beginners."),
            new Review(3L, 6L, 8L, LocalDate.parse("2022-03-04"), 1L, "Total prick. Knowing four languages does not mean you should boast all the time. Never again."),
            new Review(4L, 3L, 6L, LocalDate.parse("2021-12-08"), 5L, "I learned more in two sessions with him than in my whole highschool. He helped me get a certification, awesome dude!"),
            new Review(5L, 4L, 7L, LocalDate.parse("2021-10-05"), 3L, "He is a bit rusty on his english but means well. Perhaps he will get better.")
    );

    protected final List<ReviewDTO> mockReviewDTOs = Arrays.asList(
            new ReviewDTO(1L, 2L, 6L, LocalDate.parse("2022-02-06"), 5L, "Great guy. Wish I had met him sooner. Great speaker and teacher!"),
            new ReviewDTO(2L, 3L, 7L, LocalDate.parse("2022-08-01"), 4L, "We got off of the wrong foot but the guy is still great. Would not recommend to complete beginners."),
            new ReviewDTO(3L, 6L, 8L, LocalDate.parse("2022-03-04"), 1L, "Total prick. Knowing four languages does not mean you should boast all the time. Never again."),
            new ReviewDTO(4L, 3L, 6L, LocalDate.parse("2021-12-08"), 5L, "I learned more in two sessions with him than in my whole highschool. He helped me get a certification, awesome dude!"),
            new ReviewDTO(5L, 4L, 7L, LocalDate.parse("2021-10-05"), 3L, "He is a bit rusty on his english but means well. Perhaps he will get better.")
    );

    protected final List<Message> mockMessages = Arrays.asList(
            new Message(1L, 2L, 6L, LocalDateTime.parse("2022-06-02T12:00:00"), "Hey man. Wanna meet and talk?"),
            new Message(2L, 6L, 2L, LocalDateTime.parse("2022-06-02T15:30:00"), "Sure. I work a 9-5, would Tuesday at six be ok?"),
            new Message(3L, 2L, 6L, LocalDateTime.parse("2022-06-02T18:45:00"), "Definitely. I will let you know if plans change."),
            new Message(4L, 2L, 6L, LocalDateTime.parse("2022-06-03T09:15:00"), "Skype or Zoom?"),
            new Message(5L, 6L, 2L, LocalDateTime.parse("2022-06-03T14:00:00"), "Zoom is great, I will send you a message on Zoom that day. Cheers!"),
            new Message(6L, 6L, 1L, LocalDateTime.parse("2022-05-29T12:00:00"), "I know we got off of the wrong foot, sorry for that."),
            new Message(7L, 1L, 6L, LocalDateTime.parse("2022-05-30T15:30:00"), "No problem dude"),
            new Message(8L, 1L, 6L, LocalDateTime.parse("2022-05-30T18:45:00"), "I am just worried that will repeat in the future, I have a nasty temper."),
            new Message(9L, 6L, 1L, LocalDateTime.parse("2022-06-01T19:15:00"), "Well you will have to work on your patience..."),
            new Message(10L, 6L, 1L, LocalDateTime.parse("2022-06-01T19:15:30"), "You will get there eventually.")
    );

    protected final List<MessageDTO> mockMessageDTOs = Arrays.asList(
            new MessageDTO(1L, 2L, 6L, LocalDateTime.parse("2022-06-02T12:00:00"), "Hey man. Wanna meet and talk?"),
            new MessageDTO(2L, 6L, 2L, LocalDateTime.parse("2022-06-02T15:30:00"), "Sure. I work a 9-5, would Tuesday at six be ok?"),
            new MessageDTO(3L, 2L, 6L, LocalDateTime.parse("2022-06-02T18:45:00"), "Definitely. I will let you know if plans change."),
            new MessageDTO(4L, 2L, 6L, LocalDateTime.parse("2022-06-03T09:15:00"), "Skype or Zoom?"),
            new MessageDTO(5L, 6L, 2L, LocalDateTime.parse("2022-06-03T14:00:00"), "Zoom is great, I will send you a message on Zoom that day. Cheers!"),
            new MessageDTO(6L, 6L, 1L, LocalDateTime.parse("2022-05-29T12:00:00"), "I know we got off of the wrong foot, sorry for that."),
            new MessageDTO(7L, 1L, 6L, LocalDateTime.parse("2022-05-30T15:30:00"), "No problem dude"),
            new MessageDTO(8L, 1L, 6L, LocalDateTime.parse("2022-05-30T18:45:00"), "I am just worried that will repeat in the future, I have a nasty temper."),
            new MessageDTO(9L, 6L, 1L, LocalDateTime.parse("2022-06-01T19:15:00"), "Well you will have to work on your patience..."),
            new MessageDTO(10L, 6L, 1L, LocalDateTime.parse("2022-06-01T19:15:30"), "You will get there eventually.")
    );
}
