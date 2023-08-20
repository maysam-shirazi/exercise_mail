package com.skyeng.mailtracker.model.postalitem;

import com.skyeng.mailtracker.model.PostOffice;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "postal_item_event")
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Item postalItem;
    @Column
    private Date eventTime;
    @ManyToOne
    private EventType type;
    @ManyToOne
    private PostOffice postOffice;


}
