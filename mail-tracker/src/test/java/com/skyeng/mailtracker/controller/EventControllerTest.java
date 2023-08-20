package com.skyeng.mailtracker.controller;

import com.skyeng.mailtracker.model.PostOffice;
import com.skyeng.mailtracker.model.postalitem.Event;
import com.skyeng.mailtracker.model.postalitem.EventType;
import com.skyeng.mailtracker.model.postalitem.Item;
import com.skyeng.mailtracker.model.postalitem.ItemType;
import com.skyeng.mailtracker.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService mockService;

    @Test
    void testGetPostalItemEvents() throws Exception {
        // Setup
        // Configure EventService.getEvents(...).
        final List<Event> events = List.of(new Event(0L, new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new EventType(0L, "title")));
        when(mockService.getEvents(0L)).thenReturn(events);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/event")
                .param("itemid", "0")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[{\"id\":0,\"postalItem\":{\"id\":0,\"type\":{\"id\":0,\"title\":\"title\"},\"recipientIndex\":0,\"recipientAddress\":\"recipientAddress\",\"receiverName\":\"receiverName\",\"postOffice\":{\"id\":0,\"index\":0,\"name\":\"name\",\"address\":\"address\"}},\"eventTime\":\"2019-12-31T20:30:00.000+00:00\",\"type\":{\"id\":0,\"title\":\"title\"}}]");
    }

    @Test
    void testGetPostalItemEvents_EventServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockService.getEvents(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/event")
                .param("itemid", "0")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetPostalItemLatestEvent() throws Exception {
        // Setup
        // Configure EventService.getLatestEvent(...).
        final Event event = new Event(0L, new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new EventType(0L, "title"));
        when(mockService.getLatestEvent(0L)).thenReturn(event);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/event/item-status")
                .param("itemid", "0")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"id\":0,\"postalItem\":{\"id\":0,\"type\":{\"id\":0,\"title\":\"title\"},\"recipientIndex\":0,\"recipientAddress\":\"recipientAddress\",\"receiverName\":\"receiverName\",\"postOffice\":{\"id\":0,\"index\":0,\"name\":\"name\",\"address\":\"address\"}},\"eventTime\":\"2019-12-31T20:30:00.000+00:00\",\"type\":{\"id\":0,\"title\":\"title\"}}");
    }
}
