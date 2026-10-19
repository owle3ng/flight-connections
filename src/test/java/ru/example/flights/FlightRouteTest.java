package ru.example.flights;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class FlightRouteTest {
    private final Airport ams = new Airport("AMS", "Амстердам", ZoneId.of("Europe/Amsterdam"));
    private final Airport fra = new Airport("FRA", "Франкфурт", ZoneId.of("Europe/Berlin"));
    private final Airport hel = new Airport("HEL", "Хельсинки", ZoneId.of("Europe/Helsinki"));

    private Flight flight(String id, Airport from, Airport to, int day, int depHour, int arrDay, int arrHour) {
        return new Flight(id, from, to, LocalDateTime.of(2026, 11, day, depHour, 0), LocalDateTime.of(2026, 11, arrDay, arrHour, 0));
    }

    @Test void durationinSameZone() {
        Airport a = new Airport("LHR", "Лондон", ZoneId.of("Europe/London"));
        Airport b = new Airport("MAN", "Манчестер", ZoneId.of("Europe/London"));
        Flight f = flight("T1", a, b, 10, 8, 10, 10);
        assertEquals(Duration.ofHours(2), f.getDuration());
    }

    @Test void durationAcrossDiffTimeZones() {
        Flight f = new Flight("T2", ams, fra, LocalDateTime.of(2026, 11, 10, 8, 0), LocalDateTime.of(2026, 11, 10, 10, 0));
        assertEquals(Duration.ofHours(2), f.getDuration());
    }

    @Test void flightCanArriveAfterMidnight() {
        Flight f = flight("T3", ams, fra, 10, 23, 11, 2);
        assertEquals(Duration.ofHours(3), f.getDuration());
    }

    @Test void arrivalInstantMustBeAfterDeparture() {
        assertThrows(IllegalArgumentException.class, () -> new Flight("BAD", ams, fra, LocalDateTime.of(2026, 11, 10, 10, 0), LocalDateTime.of(2026, 11, 10, 8, 0)));
    }
}
