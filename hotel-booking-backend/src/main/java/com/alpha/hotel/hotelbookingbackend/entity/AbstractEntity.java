package com.alpha.hotel.hotelbookingbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class AbstractEntity {

    @CreationTimestamp
    @Column(name = "created_date",
            updatable = false,
            nullable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date",
            nullable = false)
    private LocalDateTime lastModifiedDate;

    @ColumnDefault("false")
    private boolean isDeleted;

}