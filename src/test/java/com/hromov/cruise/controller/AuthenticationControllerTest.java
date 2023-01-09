package com.hromov.cruise.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EmbeddedKafka
@SqlGroup({
        @Sql(value = "classpath:reset_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
        @Sql(value = "classpath:test_data.sql")
})
class AuthenticationControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    JwtDecoder jwtDecoder;

    @Test
    void testGeneratesToken() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/auth/token")
                        .with(httpBasic("test", "admin")))
                .andExpect(status().isOk())
                .andReturn();
        String token = mvcResult.getResponse().getContentAsString();

        assertNotNull(token);
        assertNotEquals("", token);
    }

    @Test
    void tokenAuthenticatesAUserForRestrictedPage() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/auth/token")
                        .with(httpBasic("test", "admin")))
                .andExpect(status().isOk())
                .andReturn();
        String token = mvcResult.getResponse().getContentAsString();

        mvc.perform(get("/passenger/350")
                        .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void tokenAvailableOnlyAfterAuthentication() throws Exception {
        mvc.perform(get("/auth/token"))
                .andExpect(status().isUnauthorized());
    }
}