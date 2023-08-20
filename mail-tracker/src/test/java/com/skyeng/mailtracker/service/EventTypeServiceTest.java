package com.skyeng.mailtracker.service;

import com.skyeng.mailtracker.model.postalitem.EventType;
import com.skyeng.mailtracker.repository.EventTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EventTypeServiceTest {
    EventTypeService eventTypeServiceUnderTest;

    @BeforeEach
    void setUp() {

        eventTypeServiceUnderTest = new EventTypeService();
        eventTypeServiceUnderTest.repository = mock(EventTypeRepository.class);
    }

    @Test
    void testGetEventTypes() {
        // Setup
        final List<EventType> expectedResult = List.of(new EventType(10L, "title"));
        when(eventTypeServiceUnderTest.repository.findAll()).thenReturn(List.of(new EventType(10L, "title")));

        // Run the test
        final List<EventType> result = eventTypeServiceUnderTest.getEventTypes();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetEventTypes_EventTypeRepositoryReturnsNoItems() {
        // Setup
        when(eventTypeServiceUnderTest.repository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<EventType> result = eventTypeServiceUnderTest.getEventTypes();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetEventType() {
        // Setup
        final Optional<EventType> expectedResult = Optional.of(new EventType(0L, "title"));
        when(eventTypeServiceUnderTest.repository.findById(0L)).thenReturn(Optional.of(new EventType(0L, "title")));

        // Run the test
        final Optional<EventType> result = eventTypeServiceUnderTest.getEventType(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetEventType_EventTypeRepositoryReturnsAbsent() {
        // Setup
        when(eventTypeServiceUnderTest.repository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<EventType> result = eventTypeServiceUnderTest.getEventType(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }
}
