package com.loncark.langoapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends BaseControllerTest {
    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("$2a$10$jfi6MfM2yLz9/EXq6MXDEefljQUBJsz7zrbHmduuqnl.ux9KPgTYi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles").value("ROLE_ADMIN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/9999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/4"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/4")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSave() throws Exception {
        String requestBody = "{\"name\": \"Paul\", " +
                "\"password\": \"paulpassword\", " +
                "\"roles\": \"ROLE_USER\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Paul"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("paulpassword"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles").value("ROLE_USER"));
    }

    @Test
    public void testUpdate() throws Exception {
        String requestBody = "{\"id\": \"1\"" +
                "\"name\": \"Paul\", " +
                "\"password\": \"paulpassword\", " +
                "\"roles\": \"ROLE_USER\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Paul"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("paulpassword"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles").value("ROLE_USER"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
}
