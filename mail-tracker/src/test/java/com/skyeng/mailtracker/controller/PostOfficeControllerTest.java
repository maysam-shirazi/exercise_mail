package com.skyeng.mailtracker.controller;

import com.skyeng.mailtracker.model.PostOffice;
import com.skyeng.mailtracker.service.PostOfficeService;
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

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostOfficeController.class)
class PostOfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostOfficeService mockService;

    @Test
    void testGetPostOffices() throws Exception {
        // Setup
        // Configure PostOfficeService.getPostOffices(...).
        final List<PostOffice> postOffices = List.of(new PostOffice(0L, 0L, "name", "address"));
        when(mockService.getPostOffices()).thenReturn(postOffices);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/post-offices")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[{\"id\":0,\"index\":0,\"name\":\"name\",\"address\":\"address\"}]");
    }

    @Test
    void testGetPostOffices_PostOfficeServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockService.getPostOffices()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/post-offices")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }
}
