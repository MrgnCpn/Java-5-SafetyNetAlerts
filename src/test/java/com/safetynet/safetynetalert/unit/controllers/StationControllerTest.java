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
class StationControllerTest {
    String data = "{\"number\" : 0, \"address\":\"951 LoneTree Rd\"}";
    String URL = "/firestation";


    @Autowired
    MockMvc mockMvc;

    @Tag("StationControllerTest")
    @Test
    void testPost() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URL)
                .accept(MediaType.APPLICATION_JSON).content(data)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.getResponse().getContentAsString()).isInstanceOf(String.class);

    }
    @Tag("StationControllerTest")
    @Test
    void testPost_emptyBody() {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URL)
                .accept(MediaType.APPLICATION_JSON).content("{}")
                .contentType(MediaType.APPLICATION_JSON);

        assertThatExceptionOfType(NestedServletException.class).isThrownBy(() -> mockMvc.perform(requestBuilder).andReturn());
    }

    @Tag("StationControllerTest")
    @Test
    void testPut() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(URL)
                .accept(MediaType.APPLICATION_JSON).content(data)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.getResponse().getContentAsString()).isInstanceOf(String.class);

    }
    @Tag("StationControllerTest")
    @Test
    void testPut_emptyBody() {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(URL)
                .accept(MediaType.APPLICATION_JSON).content("{}")
                .contentType(MediaType.APPLICATION_JSON);

        assertThatExceptionOfType(NestedServletException.class).isThrownBy(() -> mockMvc.perform(requestBuilder).andReturn());
    }

    @Tag("StationControllerTest")
    @Test
    void testDeleteMapping() throws Exception {
        mockMvc.perform(delete(URL + "/address/951 LoneTree Rd"))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(delete(URL + "/address"))
                .andExpect(status().is4xxClientError());
    }

    @Tag("StationControllerTest")
    @Test
    void testDeleteStation() throws Exception {
        mockMvc.perform(delete(URL + "/stationNumber/1"))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(delete(URL + "/stationNumber"))
                .andExpect(status().is4xxClientError());
    }
}