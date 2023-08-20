package com.skyeng.mailtracker.model.postalitem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "postal_item_event_type")
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventType {
    public static final long EVENT_TYPE_ID_REGISTER = 1;
    public static final long EVENT_TYPE_ID_ARRIVED = 2;
    public static final long EVENT_TYPE_ID_DEPARTED = 3;
    public static final long EVENT_TYPE_ID_DELIVERED = 4;
    @Id
    private Long id;
    @Column
    private String title;
}
