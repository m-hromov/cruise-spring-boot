package com.hromov.cruise.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EmbeddedKafka
class SignInControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void testLoadPage() throws Exception {
        mvc.perform(get("/sign_in"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test")
    void testReturnsAuthenticatedUser() throws Exception {
        mvc.perform(get("/sign_in/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.principal.username").value("test"));
    }

    @Test
    void testOnlyAuthenticated() throws Exception {
        mvc.perform(get("/sign_in/user"))
                .andExpect(status().isUnauthorized());
    }
}