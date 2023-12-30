package com.loncark.langoapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends BaseControllerTest {

    private String validAdminJwt;

    @BeforeEach
    public void setup() throws Exception {
        validAdminJwt = getValidAdminJwt();
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + validAdminJwt)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("$2a$10$GglJYDGHtabzwxbbeFfhle7A/W5z9OhHcPCj1RB2dUJyKItFW/d5K"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles").value("ROLE_ADMIN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/9999")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + validAdminJwt)
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
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + validAdminJwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Paul"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles").value("ROLE_USER"));
    }

    @Test
    public void testDeleteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/5")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + validAdminJwt)
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/5")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + validAdminJwt)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testUpdate() throws Exception {
        String requestBody = "{\"id\": 3, " +
                "\"name\": \"Paul\", " +
                "\"password\": \"paulpassword\", " +
                "\"roles\": \"ROLE_USER\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + validAdminJwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Paul"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles").value("ROLE_USER"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3));
    }

    @Test
    public void testGetByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users")
                        .param("name", "John")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + validAdminJwt)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("$2a$10$GglJYDGHtabzwxbbeFfhle7A/W5z9OhHcPCj1RB2dUJyKItFW/d5K"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles").value("ROLE_ADMIN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + validAdminJwt)
                        .param("name", "NonExistentUser")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetByLanguage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + validAdminJwt)
                        .param("language", "CROATIAN")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Marko"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("$2a$10$OquQc.91.x9fjj0dvIjIseAQNZ2v1fxQ7MWqSqe.GS9iHxmarR5JC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].roles").value("ROLE_USER"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Dieter"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].password").value("$2a$10$g2aBOh9uUXgPr44RTPtAL.GrXYMP0B9moKDyRb/VMIawHW7yM1wwa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].roles").value("ROLE_USER"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(7));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + validAdminJwt)
                        .param("language", "NonExistentLanguage")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

}
