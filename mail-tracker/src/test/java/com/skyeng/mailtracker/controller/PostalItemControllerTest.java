package com.skyeng.mailtracker.controller;

import com.skyeng.mailtracker.model.PostOffice;
import com.skyeng.mailtracker.model.postalitem.Item;
import com.skyeng.mailtracker.model.postalitem.ItemType;
import com.skyeng.mailtracker.service.EventService;
import com.skyeng.mailtracker.service.PostalItemService;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostalItemController.class)
class PostalItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostalItemService mockService;
    @MockBean
    private EventService mockEventService;

    @Test
    void testRegister() throws Exception {
        // Setup
        // Configure PostalItemService.register(...).
        final Item item = new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address"));
        when(mockService.register(new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")))).thenReturn(item);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/postal-item/register")
                .content("{\"id\":0,\"type\":{\"id\":0,\"title\":\"title\"},\"recipientIndex\":0,\"recipientAddress\":\"recipientAddress\",\"receiverName\":\"receiverName\",\"postOffice\":{\"id\":0,\"index\":0,\"name\":\"name\",\"address\":\"address\"}}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"id\":0,\"type\":{\"id\":0,\"title\":\"title\"},\"recipientIndex\":0,\"recipientAddress\":\"recipientAddress\",\"receiverName\":\"receiverName\",\"postOffice\":{\"id\":0,\"index\":0,\"name\":\"name\",\"address\":\"address\"}}");
    }

    @Test
    void testArrival() throws Exception {
        // Setup
        // Configure PostalItemService.arrival(...).
        final Item item = new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address"));
        when(mockService.arrival(0L)).thenReturn(item);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/postal-item/arrival")
                .content("{\"id\":0}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"id\":0,\"type\":{\"id\":0,\"title\":\"title\"},\"recipientIndex\":0,\"recipientAddress\":\"recipientAddress\",\"receiverName\":\"receiverName\",\"postOffice\":{\"id\":0,\"index\":0,\"name\":\"name\",\"address\":\"address\"}}");
    }

    @Test
    void testDeparture() throws Exception {
        // Setup
        // Configure PostalItemService.departure(...).
        final Item item = new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address"));
        when(mockService.departure(0L)).thenReturn(item);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/postal-item/departure")
                .content("{\"id\":0}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"id\":0,\"type\":{\"id\":0,\"title\":\"title\"},\"recipientIndex\":0,\"recipientAddress\":\"recipientAddress\",\"receiverName\":\"receiverName\",\"postOffice\":{\"id\":0,\"index\":0,\"name\":\"name\",\"address\":\"address\"}}");
    }

    @Test
    void testDeliver() throws Exception {
        // Setup
        // Configure PostalItemService.departure(...).
        final Item item = new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address"));
        when(mockService.departure(0L)).thenReturn(item);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/postal-item/deliver")
                .content("{\"id\":0}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"id\":0,\"type\":{\"id\":0,\"title\":\"title\"},\"recipientIndex\":0,\"recipientAddress\":\"recipientAddress\",\"receiverName\":\"receiverName\",\"postOffice\":{\"id\":0,\"index\":0,\"name\":\"name\",\"address\":\"address\"}}");
    }

    @Test
    void testGetPostalItem() throws Exception {
        // Setup
        // Configure PostalItemService.getItem(...).
        final Optional<Item> item = Optional.of(new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")));
        when(mockService.getItem(0L)).thenReturn(item);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/postal-item")
                .param("itemid", "0")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"id\":0,\"type\":{\"id\":0,\"title\":\"title\"},\"recipientIndex\":0,\"recipientAddress\":\"recipientAddress\",\"receiverName\":\"receiverName\",\"postOffice\":{\"id\":0,\"index\":0,\"name\":\"name\",\"address\":\"address\"}}");
    }

}
