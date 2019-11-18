package com.itg.programmerexercises.objects.aircraft;

import com.itg.programmerexercises.objects.aircraft.enums.AircraftSize;
import com.itg.programmerexercises.objects.aircraft.enums.AircraftType;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Aircraft have at least (but are not limited to having) the following properties: <br>
 * - type: Passenger or Cargo <br>
 * - size: Small or Large
 */
public class Aircraft {
    public static final ZoneId UTC_ZONE_ID = ZoneId.of("UTC");

    private AircraftType aircraftType;

    private AircraftSize aircraftSize;

    /**
     * Unique id
     */
    private String uniqID;

    /**
     * Date and time when aircraft was created in the system
     */
    private ZonedDateTime systemTimestamp;

    private Aircraft(AircraftBuilder aircraftBuilder) {
        this.aircraftType = aircraftBuilder.aircraftType;
        this.aircraftSize = aircraftBuilder.aircraftSize;
        this.uniqID = UUID.randomUUID().toString();
        this.systemTimestamp = ZonedDateTime.now(UTC_ZONE_ID);
    }

    public static AircraftBuilder builder() {
        return new AircraftBuilder();
    }

    public AircraftType getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
    }

    public AircraftSize getAircraftSize() {
        return aircraftSize;
    }

    public void setAircraftSize(AircraftSize aircraftSize) {
        this.aircraftSize = aircraftSize;
    }

    public String getUniqID() {
        return uniqID;
    }

    public void setUniqID(String uniqID) {
        this.uniqID = uniqID;
    }

    public ZonedDateTime getSystemTimestamp() {
        return systemTimestamp;
    }

    public void setSystemTimestamp(ZonedDateTime systemTimestamp) {
        this.systemTimestamp = systemTimestamp;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * Builder to build {@link Aircraft}.
     */
    public static final class AircraftBuilder {
        private AircraftType aircraftType;

        private AircraftSize aircraftSize;

        private AircraftBuilder() {
        }

        public AircraftBuilder aircraftType(AircraftType aircraftType) {
            this.aircraftType = aircraftType;
            return this;
        }

        public AircraftBuilder aircraftSize(AircraftSize aircraftSize) {
            this.aircraftSize = aircraftSize;
            return this;
        }

        public Aircraft build() {
            return new Aircraft(this);
        }
    }

}
