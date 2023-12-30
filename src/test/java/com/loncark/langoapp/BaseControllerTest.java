package com.loncark.langoapp;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public abstract class BaseControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    protected String getValidAdminJwt() throws Exception {
        String requestBody = "{\"username\": \"John\", " +
                "\"password\": \"johnpassword\"}";

        return getValidJwt(requestBody);
    }

    protected String getValidUserJwt() throws Exception {
        String requestBody = "{\"username\": \"Marko\", " +
                "\"password\": \"markopassword\"}";

        return getValidJwt(requestBody);
    }

    protected String getValidJwt(String requestBody) throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        JSONObject jsonResponse = new JSONObject(responseContent);

        return jsonResponse.getString("accessToken");
    }
}
