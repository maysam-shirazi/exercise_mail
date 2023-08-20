package com.skyeng.mailtracker.service;

import com.skyeng.mailtracker.model.PostOffice;
import com.skyeng.mailtracker.model.postalitem.Event;
import com.skyeng.mailtracker.model.postalitem.EventType;
import com.skyeng.mailtracker.model.postalitem.Item;
import com.skyeng.mailtracker.model.postalitem.ItemType;
import com.skyeng.mailtracker.repository.EventRepository;
import com.skyeng.mailtracker.repository.PostalItemRepository;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostalItemServiceTest {

    @Mock
    PostalItemRepository postalItemRepository;

    @Mock
    EventRepository eventRepository;

    @InjectMocks
    PostalItemService postalItemService;


    @InjectMocks
    EventService eventService;

    @InjectMocks
    PostOfficeService postOfficeService;

    public static final long ID=1L;
    @BeforeEach
    void setUp() {
        postalItemService.repository = postalItemRepository;
        eventService.repository = eventRepository;
        postalItemService.eventService = eventService;
        postalItemService.postOfficeService = postOfficeService;
    }

    private Item buildItem() {
        return Item.builder()
                .id(1L)
                .recipientIndex(5432167890L)
                .receiverName("Vladimir")
                .recipientAddress("Volzhskiy Druzhby Ul bld 121 appt 160")
                .postOffice(PostOffice.builder().id(ID).build())
                .type(ItemType.builder().id(ID)
                        .build()).build();
    }

    @Test
    void register() {
        var expected =  buildItem();
        assertThat(expected).isNotNull();
        when(postalItemRepository.save(any())).thenReturn(expected);
        var item = postalItemService.register(expected);
        Assertions.assertThat(item.getId()).isNotNull();
        EventType eventType = new EventType(1L, "registered");
        final List<Event> events = List.of(new Event(0L, new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), eventType,new PostOffice(0L, 0L, "name", "address")));
        when(eventRepository.findByPostalItemIdOrderByIdDesc(any())).thenReturn(events);
        val event = eventService.getLatestEvent(1L);
        Assertions.assertThat(event).isNotNull();
        Assertions.assertThat(event.getType().getId()).isEqualTo(EventType.EVENT_TYPE_ID_REGISTER);
    }

    @Test
    void getItem() {
        // Setup
        final Optional<Item> expectedResult = Optional.of(new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")));

        // Configure PostalItemRepository.findById(...).
        final Optional<Item> item = Optional.of(new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")));
        when(postalItemService.repository.findById(0L)).thenReturn(item);

        // Run the test
        final Optional<Item> result = postalItemService.getItem(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

   /* @Test
    void testArrival() {
        var expected =  buildItem();
        var postOffice = new PostOffice(0L, 0L, "name", "address");
        when(postalItemRepository.findById(anyLong())).thenReturn(Optional.of(expected));
        when(postalItemService.postOfficeService.getPostOffice(anyLong())).thenReturn(Optional.of(postOffice));
        postalItemService.arrival(1L,0L);
        EventType eventType = new EventType(2L, "arrived");
        final List<Event> events = List.of(new Event(0L, new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), eventType,new PostOffice(0L, 0L, "name", "address")));
        when(eventRepository.findByPostalItemIdOrderByIdDesc(any())).thenReturn(events);

        val event = eventService.getLatestEvent(1L);
        Assertions.assertThat(event).isNotNull();
        Assertions.assertThat(event.getType().getId()).isEqualTo(EventType.EVENT_TYPE_ID_ARRIVED);
    }
    @Test
    void testDeparture() {
        var expected =  buildItem();
        var postOffice = new PostOffice(0L, 0L, "name", "address");
        when(postalItemRepository.findById(anyLong())).thenReturn(Optional.of(expected));
        when(postalItemService.postOfficeService.getPostOffice(anyLong())).thenReturn(Optional.of(postOffice));
        postalItemService.departure(1L,0L);
        EventType eventType = new EventType(3L, "departured");
        final List<Event> events = List.of(new Event(0L, new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), eventType,new PostOffice(0L, 0L, "name", "address")));
        when(eventRepository.findByPostalItemIdOrderByIdDesc(any())).thenReturn(events);

        val event = eventService.getLatestEvent(1L);
        Assertions.assertThat(event).isNotNull();
        Assertions.assertThat(event.getType().getId()).isEqualTo(EventType.EVENT_TYPE_ID_DEPARTED);
    }*/
    @Test
    void testDeliver() {
        var expected =  buildItem();
        assertThat(expected).isNotNull();
        when(postalItemRepository.findById(any())).thenReturn(Optional.of(expected));
        postalItemService.deliver(1L);
        EventType eventType = new EventType(4L, "deliver");
        final List<Event> events = List.of(new Event(0L, new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), eventType,new PostOffice(0L, 0L, "name", "address")));
        when(eventRepository.findByPostalItemIdOrderByIdDesc(any())).thenReturn(events);

        val event = eventService.getLatestEvent(1L);
        Assertions.assertThat(event).isNotNull();
        Assertions.assertThat(event.getType().getId()).isEqualTo(EventType.EVENT_TYPE_ID_DELIVERED);
    }

    @Test
    void testAddItem() {
        // Setup
        final Item postalItem = new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address"));
        final Item expectedResult = new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address"));

        // Configure PostalItemRepository.save(...).
        final Item item = new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address"));
        when(postalItemService.repository.save(new Item(0L, new ItemType(0L, "title"), 0L, "recipientAddress", "receiverName", new PostOffice(0L, 0L, "name", "address")))).thenReturn(item);

        // Run the test
        final Item result = postalItemService.addItem(postalItem);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }


}