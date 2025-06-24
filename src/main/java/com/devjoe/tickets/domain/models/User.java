package com.devjoe.tickets.domain.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {
    @Id
    @Column(name  = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    // TODO organize event
    // TODO attending events;
    // TODO staffing events

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime localDateTime;

    @LastModifiedBy
    @Column(name = "updated_at", nullable = false)

    private LocalDateTime updatedAt;


}
