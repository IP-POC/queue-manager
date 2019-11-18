package com.itg.programmerexercises.api.v1.mapper;

import com.itg.programmerexercises.api.v1.model.AircraftDTO;
import com.itg.programmerexercises.objects.aircraft.Aircraft;
import com.itg.programmerexercises.objects.aircraft.enums.AircraftSize;
import com.itg.programmerexercises.objects.aircraft.enums.AircraftType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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