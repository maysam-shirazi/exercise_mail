package com.skyeng.mailtracker.service;

import com.skyeng.mailtracker.model.postalitem.ItemType;
import com.skyeng.mailtracker.repository.PostalItemTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostalItemTypeServiceTest {

    private PostalItemTypeService postalItemTypeServiceUnderTest;

    @BeforeEach
    void setUp() {
        postalItemTypeServiceUnderTest = new PostalItemTypeService();
        postalItemTypeServiceUnderTest.repository = mock(PostalItemTypeRepository.class);
    }

    @Test
    void testGetPostalItemTypes() {
        // Setup
        final List<ItemType> expectedResult = List.of(new ItemType(0L, "title"));
        when(postalItemTypeServiceUnderTest.repository.findAll()).thenReturn(List.of(new ItemType(0L, "title")));

        // Run the test
        final List<ItemType> result = postalItemTypeServiceUnderTest.getPostalItemTypes();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetPostalItemTypes_PostalItemTypeRepositoryReturnsNoItems() {
        // Setup
        when(postalItemTypeServiceUnderTest.repository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<ItemType> result = postalItemTypeServiceUnderTest.getPostalItemTypes();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
