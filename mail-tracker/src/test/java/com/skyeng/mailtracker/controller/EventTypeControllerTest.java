package com.skyeng.mailtracker.controller;

import com.skyeng.mailtracker.model.postalitem.EventType;
import com.skyeng.mailtracker.service.EventTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventTypeController.class)
class EventTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EventTypeService service;

    @Test
    public void getAllEventTypes() throws Exception {
        List<EventType> eventTypes = List.of(new EventType(10L, "registered"));
        when(service.getEventTypes()).thenReturn(eventTypes);
        this.mockMvc.perform(get("/event-types")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("[{\"id\":10,\"title\":\"registered\"}]")));
    }
}
