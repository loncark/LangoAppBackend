package com.loncark.langoapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loncark.langoapp.dto.AppointmentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/apts/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId1").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId2").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.aptDate").value("2023-05-06"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("We are gonna get to know each other."));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/apts/9999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/apts/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/apts/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSave() throws Exception {
        AppointmentDTO newAppointment = new AppointmentDTO();
        newAppointment.setUserId1(3L);
        newAppointment.setUserId2(7L);
        newAppointment.setAptDate(LocalDate.parse("2023-01-01"));
        newAppointment.setDescription("We are gonna talk some German.");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/apts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newAppointment)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId1").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId2").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$.aptDate").value("2023-01-01"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("We are gonna talk some German."));
    }

    @Test
    public void testUpdate() throws Exception {
        AppointmentDTO updatedAppointment = new AppointmentDTO();
        updatedAppointment.setId(4L);
        updatedAppointment.setUserId1(6L);
        updatedAppointment.setUserId2(8L);
        updatedAppointment.setAptDate(LocalDate.parse("2024-03-04"));
        updatedAppointment.setDescription("Online coffee is always a good idea.");

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/apts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAppointment)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId1").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId2").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.aptDate").value("2024-03-04"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Online coffee is always a good idea."));
    }
}


