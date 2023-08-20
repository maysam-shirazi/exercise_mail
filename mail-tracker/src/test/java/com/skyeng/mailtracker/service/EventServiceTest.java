package com.skyeng.mailtracker.service;

import com.skyeng.mailtracker.model.PostOffice;
import com.skyeng.mailtracker.model.postalitem.Event;
import com.skyeng.mailtracker.model.postalitem.EventType;
import com.skyeng.mailtracker.model.postalitem.Item;
import com.skyeng.mailtracker.model.postalitem.ItemType;
import com.skyeng.mailtracker.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EventServiceTest {

    private EventService eventServiceUnderTest;

    @BeforeEach
    void setUp() {
        eventServiceUnderTest = new EventService();
        eventServiceUnderTest.repository = mock(EventRepository.class);
    }

    @Test
    void testGetEvents() {
        // Setup
        final List<Event> expectedResult = List.of(new Event(0L, new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new EventType(0L, "title")));

        // Configure EventRepository.findByPostalItemId(...).
        final List<Event> events = List.of(new Event(0L, new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new EventType(0L, "title")));
        when(eventServiceUnderTest.repository.findByPostalItemId(0L)).thenReturn(events);

        // Run the test
        final List<Event> result = eventServiceUnderTest.getEvents(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetEvents_EventRepositoryReturnsNoItems() {
        // Setup
        when(eventServiceUnderTest.repository.findByPostalItemId(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<Event> result = eventServiceUnderTest.getEvents(0L);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetLatestEvent() {
        // Setup
        final Event expectedResult = new Event(0L, new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new EventType(0L, "title"));

        // Configure EventRepository.findByPostalItemId(...).
        final List<Event> events = List.of(new Event(0L, new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new EventType(0L, "title")));
        when(eventServiceUnderTest.repository.findByPostalItemIdOrderByIdDesc(0L)).thenReturn(events);

        // Run the test
        final Event result = eventServiceUnderTest.getLatestEvent(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetLatestEvent_EventRepositoryReturnsNoItems() {
        // Setup
        when(eventServiceUnderTest.repository.findByPostalItemId(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final Event result = eventServiceUnderTest.getLatestEvent(0L);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testAddEvent() {
        // Setup
        final Event expectedResult = new Event(0L, new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new EventType(0L, "title"));

        final Item item = new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address"));
        // Configure EventRepository.save(...).
        Event event = new Event(0L, new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new EventType(0L, "title"));
        when(eventServiceUnderTest.repository.save(any(Event.class))).thenReturn(event);

        // Run the test
        Event result = eventServiceUnderTest.addEvent(item, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
