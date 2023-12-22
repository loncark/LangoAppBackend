package com.loncark.langoapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loncark.langoapp.domain.Message;
import com.loncark.langoapp.dto.MessageDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetById() throws Exception {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(1L);
        messageDTO.setSenderId(2L);
        messageDTO.setReceiverId(6L);
        messageDTO.setDateSent(LocalDateTime.parse("2022-06-02T12:00:00"));
        messageDTO.setMessageText("Hey man. Wanna meet and talk?");

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/messages/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.senderId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.receiverId").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateSent").value("2022-06-02T12:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.messageText").value("Hey man. Wanna meet and talk?"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/messages/9999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/messages/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/messages/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSave() throws Exception {
        Message newMessage = new Message();
        newMessage.setSenderId(2L);
        newMessage.setReceiverId(6L);
        newMessage.setDateSent(LocalDateTime.parse("2022-06-02T18:45:00"));
        newMessage.setMessageText("Definitely. I will let you know if plans change.");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newMessage)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.senderId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.receiverId").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateSent").value("2022-06-02T18:45:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.messageText").value("Definitely. I will let you know if plans change."));
    }

    @Test
    public void testUpdate() throws Exception {
        Message updatedMessage = new Message();
        updatedMessage.setId(5L);
        updatedMessage.setSenderId(2L);
        updatedMessage.setReceiverId(6L);
        updatedMessage.setDateSent(LocalDateTime.parse("2022-06-03T09:15:00"));
        updatedMessage.setMessageText("Skype or Zoom?");

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedMessage)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.senderId").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.receiverId").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateSent").value("2022-06-03T09:15:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.messageText").value("Skype or Zoom?"));
    }

    @Test
    public void testGetBySenderIdAndReceiverId() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/messages")
                        .param("senderId", "2")
                        .param("receiverId", "6")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<Map<String, Object>> messages = objectMapper.readValue(jsonResponse, new TypeReference<>() {});

        Assertions.assertEquals(3, messages.size());

        for (Map<String, Object> message : messages) {
            Assertions.assertEquals(2, message.get("senderId"));
            Assertions.assertEquals(6, message.get("receiverId"));
        }
    }
}

