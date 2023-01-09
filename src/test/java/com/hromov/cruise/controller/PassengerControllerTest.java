package com.hromov.cruise.controller;

import com.hromov.cruise.listener.PassengerKafkaListener;
import com.hromov.cruise.model.Passenger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
@EmbeddedKafka(topics = "cruise.passenger")
class PassengerControllerTest {
    private final static String EXPECTED_JSON_PASSENGER = """
            [{
            "id": 349,
            "firstName": "admin",
            "lastName": "Hromov",
            "phone": "+380953178344",
            "money": 9568,
            "documentPath": "",
            "user": {
                "id": 361,
                "username": "admin",
                "password": "$2a$04$kPnAoWux201JVpj5RFSiruCaEgVB/IiEKQVYU1Y1oqHlBTxTnYHy2",
                "authorities": [
                    {
                        "id": 1,
                        "authority": "ADMIN"
                    }
                ],
                "accountNonExpired": true,
                "accountNonLocked": true,
                "credentialsNonExpired": true,
                "enabled": true
                }
            }]""";
    @Value("${server.port}")
    private int port;
    @Autowired
    private MockMvc mvc;
    @SpyBean
    private RestTemplate restTemplate;
    @Autowired
    private PassengerKafkaListener listener;

    @Test
    void loadAllPassengersIsRestrictedForUnauthorized() throws Exception {
        mvc.perform(get("/passenger/all"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:reset_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
            @Sql(value = "classpath:test_data.sql")
    })
    @WithMockUser(authorities = "ADMIN")
    void loadsAllPassengers() throws Exception {
        mvc.perform(get("/passenger/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:reset_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
            @Sql(value = "classpath:test_data.sql")
    })
    @WithMockUser(authorities = "ADMIN")
    void loadsAllPassengersWithRestTemplate() throws Exception {
        doReturn(new Passenger[]{}).when(restTemplate)
                .getForObject("http://localhost:" + port + "/passenger/all",
                        Passenger[].class);

        mvc.perform(get("/passenger/allRest"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(restTemplate, times(1))
                .getForObject("http://localhost:" + port + "/passenger/all",
                        Passenger[].class);
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:reset_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
            @Sql(value = "classpath:test_data.sql")
    })
    @WithMockUser(authorities = "ADMIN")
    void loadsAllPassengersWithMockRestServiceServer() throws Exception {
        MockRestServiceServer mock = MockRestServiceServer.bindTo(restTemplate).build();
        mock.expect(once(), requestTo("http://localhost:" + port + "/passenger/all"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(EXPECTED_JSON_PASSENGER, MediaType.APPLICATION_JSON));

        mvc.perform(get("/passenger/allRest"))
                .andExpect(status().isOk())
                .andExpect(content().json(EXPECTED_JSON_PASSENGER));
        mock.verify();
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    @SqlGroup({
            @Sql(value = "classpath:reset_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
            @Sql(value = "classpath:test_data.sql")
    })
    void loadPassengerById() throws Exception {
        mvc.perform(get("/passenger/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("testName"))
                .andExpect(jsonPath("$.lastName").value("testLastName"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.user.username").value("test"))
                .andExpect(jsonPath("$.user.id").value(1));

        Thread.sleep(1000);
        Passenger received = listener.getReceived();
        assertNotNull(received);
        assertEquals(received.getFirstName(), "testName");
    }
}