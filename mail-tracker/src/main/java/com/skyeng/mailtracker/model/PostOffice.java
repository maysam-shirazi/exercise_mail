package com.skyeng.mailtracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "post_office")
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostOffice {
    @Id
    private Long id;
    @Column
    private Long index;
    @Column
    private String name;
    //Manual says "address of the recipient" that does not make sense to me!
    @Column
    private String address;
}
