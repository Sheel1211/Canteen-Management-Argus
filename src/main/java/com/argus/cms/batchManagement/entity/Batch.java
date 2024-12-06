package com.argus.cms.batchManagement.entity;

import com.argus.cms.batchManagement.TimeSlot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_batches")
@NoArgsConstructor
@Getter
@Setter
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private TimeSlot timeSlot;
}