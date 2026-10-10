package ru.example.flights;
import java.time.ZoneId;
import java.util.Objects;

/** Аэропорт с кодом IATA, названием и часовым поясом. */
public final class Airport implements Identifiable {
    private final String iataCode;
    private final String name;
    private final ZoneId zoneId;

    public Airport(String iataCode, String name, ZoneId zoneId) {
        if (iataCode == null || !iataCode.matches("[A-Z]{3}")) {
            throw new IllegalArgumentException("Это не код IATA");
        }
        this.iataCode = iataCode;
        this.name = Objects.requireNonNull(name, " name обязательно");
        this.zoneId = Objects.requireNonNull(zoneId, "zoneId обязателен");
    }

    @Override public String getId() {
        return iataCode;
    }

    public String getIataCode() {
        return iataCode;
    }

    public String getName() {
        return name;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }
    /** 2 Аэропорта равны, если совпадают их IATA */
    @Override public boolean equals(Object other) {
        return this == other || other instanceof Airport airport && iataCode.equals(airport.iataCode);
    }
    @Override public String toString() {
        return iataCode + " — " + name;
    }
}
