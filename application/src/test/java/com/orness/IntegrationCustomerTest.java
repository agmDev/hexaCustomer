
package com.orness;

import com.orness.entities.AgifyEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(value = "/dataset.sql")
@ActiveProfiles("test")
class IntegrationCustomerTest {
    private static final String GET_CUSTOMER_ENDPOINT = "/customers/{mail}";
    private static final String POST_CUSTOMERS_ENDPOINT = "/customers";
    @Autowired
    private MockMvc mvc;
    @MockBean
    private RestTemplate agifyClient;

    @Test
    void shouldReturn404ForWrongmailAddress() throws Exception {
        mvc.perform(get(GET_CUSTOMER_ENDPOINT, "wrong")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().json(
                        "{\"title\": \"NOT_FOUND\", \"description\": \"Customer not found\"}"));
    }

    //@Sql(value = "/dataset.sql")
    @Test
    void shouldReturn200ForCorrectmailAddress() throws Exception {
        mvc.perform(get(GET_CUSTOMER_ENDPOINT, "ck@gmail.com")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().json(
                        "{\"firstname\":\"Corvus\",\"lastname\":\"Korax\",\"age\":75,\"mail\":\"ck@gmail.com\"}"));
    }

    @Test
    void shouldReturn200ForCorrectInput() throws Exception {
        mvc.perform(post(POST_CUSTOMERS_ENDPOINT).contentType("application/json").content("{\n\"firstname\": \"nicholas\",\n\"lastname\": \"Wolfwood\",\n\"age\": 37,\n\"mail\": \"nic.wolf@orange.fr\"\n}"))
                .andExpect(status().isOk());

        mvc.perform(get(GET_CUSTOMER_ENDPOINT, "nic.wolf@orange.fr")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().json(
                        "{\"firstname\":\"nicholas\",\"lastname\":\"Wolfwood\",\"age\":37,\"mail\":\"nic.wolf@orange.fr\"}"));
    }

    @Test
    void shouldReturn200ForCorrectInputWithoutAge() throws Exception {

        AgifyEntity agifyResponse = new AgifyEntity();
        agifyResponse.setCount(1000);
        agifyResponse.setAge(29);
        Mockito.when(agifyClient.getForObject(eq("/?name=nicholas"), eq(AgifyEntity.class), anyMap())).thenReturn(agifyResponse);
        mvc.perform(post(POST_CUSTOMERS_ENDPOINT).contentType("application/json").content("{\n\"firstname\": \"nicholas\",\n\"lastname\": \"Wolfwood\",\n\"mail\": \"nic.wolf@orange.fr\"\n}"))
                .andExpect(status().isOk());

        mvc.perform(get(GET_CUSTOMER_ENDPOINT, "nic.wolf@orange.fr")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().json(
                        "{\"firstname\":\"nicholas\",\"lastname\":\"Wolfwood\",\"age\":0,\"mail\":\"nic.wolf@orange.fr\"}"));
    }
    @Test
    void shouldReturn400ForIncorrectmail() throws Exception {
        mvc.perform(post(POST_CUSTOMERS_ENDPOINT).contentType("application/json").content("{\n\"firstname\": \"nicholas\",\n\"lastname\": \"Wolfwood\",\n\"age\": 37,\n\"mail\": \"37\"\n}"))
                .andExpect(status().isBadRequest()).andExpect(content().json("{\r\n    \"description\": \"Error occurred: Email should be valid\",\r\n    \"title\": \"BAD_REQUEST\"\r\n}"));

        mvc.perform(get(GET_CUSTOMER_ENDPOINT, "37")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().json(
                        "{\"title\": \"NOT_FOUND\", \"description\": \"Customer not found\"}"));
    }

    //@Sql(value = "/dataset.sql")
    @Test
    void shouldReturn400ForExistingmail() throws Exception {
        mvc.perform(post(POST_CUSTOMERS_ENDPOINT).contentType("application/json").content("{\n\"firstname\": \"nicholas\",\n\"lastname\": \"Wolfwood\",\n\"age\": 37,\n\"mail\": \"ck@gmail.com\"\n}"))
                .andExpect(status().isConflict()).andExpect(content().json("{\r\n    \"description\": \"Email already taken\",\r\n    \"title\": \"CONFLICT\"\r\n}"));

        mvc.perform(get(GET_CUSTOMER_ENDPOINT, "ck@gmail.com")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().json(
                        "{\"firstname\":\"Corvus\",\"lastname\":\"Korax\",\"age\":75,\"mail\":\"ck@gmail.com\"}"));
    }
}
