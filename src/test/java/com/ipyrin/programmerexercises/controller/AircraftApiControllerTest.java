package com.ipyrin.programmerexercises.controller;

import com.ipyrin.programmerexercises.objects.aircraft.enums.AircraftSize;
import com.ipyrin.programmerexercises.objects.aircraft.enums.AircraftType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AircraftApiControllerTest {
    private static final String aircraftDtoJson = "{\"aircraftType\":\"%s\",\"aircraftSize\":\"%s\"}";

    @Autowired
    MockMvc mockMvc;

    @Test
    public void systemBoot() throws Exception {
        mockMvc.perform(post(AircraftApiController.BASE_URL + "/systemboot")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void enqueueAircraft() throws Exception {
        String type = AircraftType.PASSENGER.toString();
        String size = AircraftSize.SMALL.toString();
        mockMvc.perform(post(AircraftApiController.BASE_URL + "/enqueue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format(aircraftDtoJson, type, size)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.aircraftType", is(type)))
                .andExpect(jsonPath("$.aircraftSize", is(size)));
    }

    @Test
    public void dequeueAircraft() throws Exception {
        String type = AircraftType.PASSENGER.toString();
        String size = AircraftSize.SMALL.toString();
        mockMvc.perform(post(AircraftApiController.BASE_URL + "/enqueue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format(aircraftDtoJson, type, size)));

        mockMvc.perform(post(AircraftApiController.BASE_URL + "/dequeue")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.aircraftType", is(type)))
                .andExpect(jsonPath("$.aircraftSize", is(size)));
    }

    @Test
    public void getQueueSize() throws Exception {
        String type = AircraftType.PASSENGER.toString();
        String size = AircraftSize.SMALL.toString();
        mockMvc.perform(post(AircraftApiController.BASE_URL + "/enqueue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format(aircraftDtoJson, type, size)));

        mockMvc.perform(get(AircraftApiController.BASE_URL + "/queuesize")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}