package com.devjoe.tickets.domain.models;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Builder
@Getter
@Setter
@Table(name = "ticket_types")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TicketType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TicketType that = (TicketType) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(totalAvailable, that.totalAvailable) && Objects.equals(event, that.event) && Objects.equals(localDateTime, that.localDateTime) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, totalAvailable, event, localDateTime, updatedAt);
    }

    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "total_available", nullable = true)
    private Integer totalAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;


    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime localDateTime;


    @OneToMany(mappedBy = "ticketType", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

    @LastModifiedBy
    @Column(name = "updated_at", nullable = false)

    private LocalDateTime updatedAt;









}
