package com.skyeng.mailtracker.controller;

import com.skyeng.mailtracker.model.postalitem.ItemType;
import com.skyeng.mailtracker.service.PostalItemTypeService;
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
@WebMvcTest(PostalItemTypeController.class)
class PostalItemTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostalItemTypeService mockService;

    @Test
    void testGetPostalItemTypes() throws Exception {
        // Setup
        when(mockService.getPostalItemTypes()).thenReturn(List.of(new ItemType(0L, "title")));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/postal-item-types")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualToIgnoringWhitespace("[{\"id\":0,\"title\":\"title\"}]");
    }

    @Test
    void testGetPostalItemTypes_PostalItemTypeServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockService.getPostalItemTypes()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/postal-item-types")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }
}
