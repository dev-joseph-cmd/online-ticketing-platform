package com.devjoe.tickets.domain.services.impl;

import com.devjoe.tickets.domain.dto.requests.CreateEventRequest;
import com.devjoe.tickets.domain.dto.requests.CreateTicketTypeRequest;
import com.devjoe.tickets.domain.exception.UserNotFoundException;
import com.devjoe.tickets.domain.models.Event;
import com.devjoe.tickets.domain.models.TicketType;
import com.devjoe.tickets.domain.models.User;
import com.devjoe.tickets.domain.repositories.EventRepository;
import com.devjoe.tickets.domain.repositories.UserRepository;
import com.devjoe.tickets.domain.services.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class EventImpl implements EventService {

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    @Override
    public Event createEvent(UUID organizerId,final CreateEventRequest createEventRequest, String sessionId) {

        Objects.requireNonNull(createEventRequest, "Create event request cannot be null");

        log.info("[{}] About to create event with event request {}", sessionId, sessionId);

        log.info("[{}] Verifying if event organizer with ID {}  exists ",sessionId,organizerId);

        User eventOrganizer = userRepository.findById(organizerId)
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("Event organizer with ID %S does not exist", organizerId.toString())
                ));

        log.info(String.format("[%s] event organizer with ID %s exit.\n About to create event",sessionId, organizerId));

        List<CreateTicketTypeRequest> createTicketTypeRequests = createEventRequest.getTicket();
        log.info("[{}] Ticket types request was passed as request ? {}",sessionId, createTicketTypeRequests != null);

        Event createdEvent = new Event();
        createdEvent.setName(
                createEventRequest.getName() != null ? createEventRequest.getName() : "Default"
        );
        createdEvent.setStartDate(createEventRequest.getStart());
        createdEvent.setEndDate(createEventRequest.getEnd());
        createdEvent.setVenue(createEventRequest.getVenue());
        createdEvent.setSalesEnd(createEventRequest.getSaleEnd());
        createdEvent.setSalesStart(createEventRequest.getSaleEnd());
        createdEvent.setStatus(createEventRequest.getStatus());
        createdEvent.setOrganizer(eventOrganizer);

        if(createTicketTypeRequests != null && createTicketTypeRequests.isEmpty()){
            log.info("[{}] Received request for ticket type request {} ", sessionId,createEventRequest);

            List<TicketType> ticketTypes = createTicketTypeRequests
                    .stream().map(ticketType -> {
                        TicketType type = new TicketType();
                        type.setName(ticketType.getName());
                        type.setDescription(ticketType.getDescription());
                        type.setPrice(ticketType.getPrice());
                        type.setTotalAvailable(ticketType.getTotalAvailable());
                        return type;


                    }).toList();

            log.info("[{}} Ticket types to be saved {}", sessionId, ticketTypes);
            createdEvent.setTicketTypes(ticketTypes);

        }
       return eventRepository.save(createdEvent);



    }
}
