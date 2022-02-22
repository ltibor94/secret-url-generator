package com.tibor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tibor.model.SecretData;
import com.tibor.service.SecretService;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SecretController.class)
class SecretControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SecretService secretService;

    @Test
    public void shouldSaveInput() throws Exception {

        var request = post("/secret")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils
                        .toString(new UrlEncodedFormEntity(Arrays.asList(
                                new BasicNameValuePair("secret", "testSecret"),
                                new BasicNameValuePair("expireAfterViews", "12"),
                                new BasicNameValuePair("expireAfter", "2")
                        ))));

        var response = createTestData();

        when(secretService.saveSecretData(any())).thenReturn(response);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));

        verify(secretService).saveSecretData(any());
    }

    @Test
    public void shouldRetrieveHashValue() throws Exception {

        var request = get("/secret/hash").accept(MediaType.APPLICATION_JSON);
        var responseData = createTestData();
        when(secretService.retrieveByHash("hash")).thenReturn(Optional.of(responseData));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseData)));

    }

    private SecretData createTestData() {
        return new SecretData(1, "hash", "secretText", new Date(), new Date(), 50, true);
    }

}