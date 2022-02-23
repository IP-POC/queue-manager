package com.ipyrin.programmerexercises.api.v1.mapper;

import com.ipyrin.programmerexercises.api.v1.model.AircraftDTO;
import com.ipyrin.programmerexercises.objects.aircraft.Aircraft;
import com.ipyrin.programmerexercises.objects.aircraft.enums.AircraftSize;
import com.ipyrin.programmerexercises.objects.aircraft.enums.AircraftType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AircraftMapperTest {

    AircraftMapper subject = AircraftMapper.INSTANCE;

    @Test
    public void aircraftDtoToAircraft() {
        //given
        AircraftDTO aircraftDto = new AircraftDTO();
        aircraftDto.setAircraftType("PASSENGER");
        aircraftDto.setAircraftSize("LARGE");

        //when
        Aircraft aircraft = subject.aircraftDtoToAircraft(aircraftDto);

        assertEquals(AircraftType.PASSENGER, aircraft.getAircraftType());
        assertEquals(AircraftSize.LARGE, aircraft.getAircraftSize());
    }

    @Test
    public void aircraftToAircraftDTO() {
        //given
        Aircraft aircraft = Aircraft.builder()
                .aircraftType(AircraftType.CARGO)
                .aircraftSize(AircraftSize.SMALL)
                .build();

        //when
        AircraftDTO aircraftDto = subject.aircraftToAircraftDTO(aircraft);

        //then
        assertEquals("CARGO", aircraftDto.getAircraftType());
        assertEquals("SMALL", aircraftDto.getAircraftSize());
    }
}