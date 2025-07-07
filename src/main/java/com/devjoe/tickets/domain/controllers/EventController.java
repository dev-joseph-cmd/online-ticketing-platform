package com.devjoe.tickets.domain.controllers;


import com.devjoe.tickets.domain.dto.mappers.EventMapper;
import com.devjoe.tickets.domain.dto.requests.CreateEventRequest;
import com.devjoe.tickets.domain.dto.response.CreateEventResponse;
import com.devjoe.tickets.domain.models.Event;
import com.devjoe.tickets.domain.services.EventService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(params = "/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventMapper eventMapper;
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<CreateEventResponse> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequest createEventRequest,
            HttpServletRequest request
            ){

        String requestId = request.getSession().getId();
        UUID userId = UUID.fromString(jwt.getSubject());

        Event createdEvent = eventService.createEvent(userId, createEventRequest, requestId);

        CreateEventResponse createEventResponse = eventMapper.toDto(createdEvent);

        return new ResponseEntity<>(createEventResponse, HttpStatus.OK);

    }
}
