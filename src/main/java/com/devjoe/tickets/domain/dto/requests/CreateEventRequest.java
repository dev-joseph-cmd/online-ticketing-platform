package com.devjoe.tickets.domain.dto.requests;


import com.devjoe.tickets.domain.EventStatus;
import com.devjoe.tickets.domain.models.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequest {
    @NotBlank(message = "Event name is required")
    private String name;

    private LocalDateTime start;

    private LocalDateTime end;

    @NotBlank(message = "Venue for event is a required field")
    private String venue;

    private LocalDateTime saleStart;

    private LocalDateTime saleEnd;

    @NotNull(message = "Event status must be provided")
    private EventStatus status;

    private User organizer;

    @NotEmpty(message = "At least one ticket type is required")
    @Valid
    private List<CreateTicketTypeRequest> ticket = new ArrayList<>();





}
