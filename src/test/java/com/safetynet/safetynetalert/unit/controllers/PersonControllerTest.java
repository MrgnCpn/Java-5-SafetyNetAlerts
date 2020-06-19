package com.safetynet.safetynetalert.unit.controllers;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
@ComponentScan({"com.safetynet.safetynetalert"})
class PersonControllerTest {
    String person = "{\"id\" : 0,\"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"address\":\"951 LoneTree Rd\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-7458\", \"email\":\"gramps@email.com\"}";

    @Autowired
    MockMvc mockMvc;

    @Tag("PersonControllerTest")
    @Test
    void testPost() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/person")
                .accept(MediaType.APPLICATION_JSON).content(person)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.getResponse().getContentAsString()).isInstanceOf(String.class);

    }
    @Tag("PersonControllerTest")
    @Test
    void testPost_emptyBody() {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/person")
                .accept(MediaType.APPLICATION_JSON).content("{}")
                .contentType(MediaType.APPLICATION_JSON);

        assertThatExceptionOfType(NestedServletException.class).isThrownBy(() -> mockMvc.perform(requestBuilder).andReturn());
    }

    @Tag("PersonControllerTest")
    @Test
    void testPut() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/person")
                .accept(MediaType.APPLICATION_JSON).content(person)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.getResponse().getContentAsString()).isInstanceOf(String.class);

    }
    @Tag("PersonControllerTest")
    @Test
    void testPut_emptyBody() {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/person")
                .accept(MediaType.APPLICATION_JSON).content("{}")
                .contentType(MediaType.APPLICATION_JSON);

        assertThatExceptionOfType(NestedServletException.class).isThrownBy(() -> mockMvc.perform(requestBuilder).andReturn());
    }

    @Tag("PersonControllerTest")
    @Test
    void testDelete_emptyBody() throws Exception {
        mockMvc.perform(delete("/person/1"))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(delete("/person"))
                .andExpect(status().is4xxClientError());
    }
}