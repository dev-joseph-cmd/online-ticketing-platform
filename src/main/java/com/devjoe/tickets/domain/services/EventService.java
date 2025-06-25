package com.devjoe.tickets.domain.services;

import com.devjoe.tickets.domain.dto.requests.CreateEventRequest;
import com.devjoe.tickets.domain.models.Event;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, final CreateEventRequest createEventRequest, final String sessionId);
}
