package com.ipyrin.programmerexercises.api.v1.mapper;

import com.ipyrin.programmerexercises.api.v1.model.AircraftDTO;
import com.ipyrin.programmerexercises.objects.aircraft.Aircraft;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AircraftMapper {

    AircraftMapper INSTANCE = Mappers.getMapper(AircraftMapper.class);

    Aircraft aircraftDtoToAircraft(AircraftDTO aircraftDto);

    AircraftDTO aircraftToAircraftDTO(Aircraft aircraft);
}
