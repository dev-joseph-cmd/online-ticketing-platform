package com.devjoe.tickets.domain.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketTypeResponse {
    private UUID id;
    private String name;
    private Double price;
    private Integer totalAvailable;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
