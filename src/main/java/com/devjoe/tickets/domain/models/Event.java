package com.devjoe.tickets.domain.models;

import com.devjoe.tickets.domain.EventStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
@Getter
@Setter
@Entity
public class Event {

    @Id
    @Column(name = "id", updatable = false, nullable = true)
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start")
    private LocalDateTime start;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(name, event.name) && Objects.equals(start, event.start) && Objects.equals(end, event.end) && Objects.equals(venue, event.venue) && Objects.equals(salesStart, event.salesStart) && Objects.equals(saleEnd, event.saleEnd) && status == event.status && Objects.equals(localDateTime, event.localDateTime) && Objects.equals(updatedAt, event.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, start, end, venue, salesStart, saleEnd, status, localDateTime, updatedAt);
    }

    @Column(name = "end")
    private LocalDateTime end;

    @Column(name = "venue", nullable = false)
    private String venue;

    @Column(name = "sales_start")
    private LocalDateTime salesStart;

    @Column(name = "sales_end")
    private LocalDateTime saleEnd;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private User organizer;


    @ManyToMany(mappedBy = "attendingEvents")
    private List<User> attendees = new ArrayList<>();

    @ManyToMany(mappedBy = "staffingEvents")
    private List<User> staff = new ArrayList<>();



    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    List<TicketType> ticketTypes = new ArrayList<>();


    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime localDateTime;

    @LastModifiedBy
    @Column(name = "updated_at", nullable = false)

    private LocalDateTime updatedAt;





}
