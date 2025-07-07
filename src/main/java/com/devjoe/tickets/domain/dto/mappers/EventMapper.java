package com.devjoe.tickets.domain.dto.mappers;


import com.devjoe.tickets.domain.dto.response.CreateEventResponse;
import com.devjoe.tickets.domain.models.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateEventResponse toDto(Event event);

}
