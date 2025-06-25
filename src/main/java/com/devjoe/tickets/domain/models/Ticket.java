package com.devjoe.tickets.domain.models;


import com.devjoe.tickets.domain.TicketStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Ticket {
    @Id
    @Column(name = "id", nullable = false, updatable = false )
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;


    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TicketStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_type_id")
    private TicketType ticketType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaser_id")
    private User purchase;


    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<TicketValidation> validations = new ArrayList<>();



    @LastModifiedBy
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updateAt;




    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    List<QrCode> qrCodes = new ArrayList<>();


}
