package com.devjoe.tickets.domain.models;


import com.devjoe.tickets.domain.QrCodeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "qr_codes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class QrCode {

    @Id
    @Column(name = "id", nullable = false, updatable = false )
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private QrCodeStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;



}
