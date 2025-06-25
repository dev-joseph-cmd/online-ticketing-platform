package com.devjoe.tickets.domain.dto.response;


import com.devjoe.tickets.domain.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventResponse {

    private String name;
    private LocalDateTime start;
    private String venue;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private EventStatus status;
    private List<CreateTicketTypeResponse> ticketTypes;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;


}
