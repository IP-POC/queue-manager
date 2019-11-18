package com.itg.programmerexercises.api.v1.mapper;

import com.itg.programmerexercises.api.v1.model.AircraftDTO;
import com.itg.programmerexercises.objects.aircraft.Aircraft;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AircraftMapper {

    AircraftMapper INSTANCE = Mappers.getMapper(AircraftMapper.class);

    Aircraft aircraftDtoToAircraft(AircraftDTO aircraftDto);

    AircraftDTO aircraftToAircraftDTO(Aircraft aircraft);
}
