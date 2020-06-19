package com.safetynet.safetynetalert.unit.controllers;

import com.safetynet.safetynetalert.services.InformationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
@ComponentScan({"com.safetynet.safetynetalert.configuration", "com.safetynet.safetynetalert.controllers"})
class InformationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    InformationService informationService;

    @Test
    void getMapping() throws Exception {
        mockMvc.perform(get("/firestation"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        mockMvc.perform(get("/childAlert"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        mockMvc.perform(get("/phoneAlert"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        mockMvc.perform(get("/fire"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        mockMvc.perform(get("/flood/stations"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        mockMvc.perform(get("/personInfo"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        mockMvc.perform(get("/communityEmail"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }
}