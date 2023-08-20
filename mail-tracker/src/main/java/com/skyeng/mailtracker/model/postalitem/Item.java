package com.skyeng.mailtracker.model.postalitem;


import com.skyeng.mailtracker.model.PostOffice;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_item")
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private ItemType type;
    @Column
    private Long recipientIndex;
    @Column
    private String recipientAddress;
    @Column
    private String receiverName;
    @ManyToOne
    private PostOffice postOffice;
}
