package com.devjoe.tickets.domain.services;

import com.devjoe.tickets.domain.dto.requests.CreateEventRequest;
import com.devjoe.tickets.domain.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, final CreateEventRequest createEventRequest, final String sessionId);
    Page<Event> listEventsForOrganizer(UUID organizerId, Pageable pageable);


}
