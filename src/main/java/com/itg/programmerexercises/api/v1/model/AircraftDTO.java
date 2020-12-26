package com.itg.programmerexercises.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;

public class AircraftDTO {
    @Schema(description = "Type of the aircraft", required = true)
    @NotBlank
    private String aircraftType;
    @Schema(description = "Size of the aircraft", required = true)
    @NotBlank
    private String aircraftSize;

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public String getAircraftSize() {
        return aircraftSize;
    }

    public void setAircraftSize(String aircraftSize) {
        this.aircraftSize = aircraftSize;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
