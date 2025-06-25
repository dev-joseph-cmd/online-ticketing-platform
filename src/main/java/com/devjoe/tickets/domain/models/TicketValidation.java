package com.devjoe.tickets.domain.models;


import com.devjoe.tickets.domain.TicketValidationMethod;
import com.devjoe.tickets.domain.TicketValidationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ticket_validations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TicketValidation {

    @Id
    @Column(name = "id", nullable = false, updatable = false )
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;


    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketValidationStatus status;


    @Column(name = "validation", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketValidationMethod validationMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;


    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;


    @LastModifiedBy
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updateAt;


}
