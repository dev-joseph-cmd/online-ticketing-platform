package com.devjoe.tickets.domain.dto.requests;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketTypeRequest {

    @NotBlank(message = "Ticket type name is required")
    private String name;

    @NotNull(message = "Ticket type price is required")
    @PositiveOrZero(message = "Price cannot be negative")
    private Double price;

    private String description;


    private Integer totalAvailable;
}
