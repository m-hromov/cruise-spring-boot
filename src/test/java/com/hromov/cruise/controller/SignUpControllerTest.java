package com.hromov.cruise.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EmbeddedKafka
class SignUpControllerTest {
    private final static String EXPECTED_JSON_PASSENGER = """
            {
            "firstName": "admin",
            "lastName": "Hromov",
            "phone": "+380953178344",
            "money": 1000,
            "documentPath": "",
            "user": {
                "username": "test@gmail.com",
                "password": "Test05505",
                "authorities": [
                    {
                        "authority": "ADMIN"
                    }
                ],
                "accountNonExpired": true,
                "accountNonLocked": true,
                "credentialsNonExpired": true,
                "enabled": true
                }
            }""";
    @Autowired
    private MockMvc mvc;

    @Test
    void loadPage() throws Exception {
        mvc.perform(get("/sign_in"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors());
    }

    @Test
    void signUp() throws Exception {
        mvc.perform(post("/sign_up")
                        .content(EXPECTED_JSON_PASSENGER)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}