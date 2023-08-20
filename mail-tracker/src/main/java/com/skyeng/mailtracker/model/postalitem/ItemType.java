package com.skyeng.mailtracker.model.postalitem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "post_item_type")
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemType {
    @Id
    private Long id;
    @Column
    private String title;
}
