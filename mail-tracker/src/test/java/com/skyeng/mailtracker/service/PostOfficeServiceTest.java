package com.skyeng.mailtracker.service;

import com.skyeng.mailtracker.model.PostOffice;
import com.skyeng.mailtracker.repository.PostOfficeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostOfficeServiceTest {

    private PostOfficeService postOfficeServiceUnderTest;

    @BeforeEach
    void setUp() {
        postOfficeServiceUnderTest = new PostOfficeService();
        postOfficeServiceUnderTest.repository = mock(PostOfficeRepository.class);
    }

    @Test
    void testGetPostOffices() {
        // Setup
        final List<PostOffice> expectedResult = List.of(new PostOffice(0L, 0L, "name", "address"));

        // Configure PostOfficeRepository.findAll(...).
        final List<PostOffice> postOffices = List.of(new PostOffice(0L, 0L, "name", "address"));
        when(postOfficeServiceUnderTest.repository.findAll()).thenReturn(postOffices);

        // Run the test
        final List<PostOffice> result = postOfficeServiceUnderTest.getPostOffices();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetPostOffices_PostOfficeRepositoryReturnsNoItems() {
        // Setup
        when(postOfficeServiceUnderTest.repository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<PostOffice> result = postOfficeServiceUnderTest.getPostOffices();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
