package ru.example.flights;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;


public final class Flight implements Identifiable {
    private final String flightNumber;
    private final Airport departureAirport;
    private final Airport arrivalAirport;
    private final LocalDateTime localDeparture;
    private final LocalDateTime localArrival;

    public Flight(String flightNumber, Airport departureAirport, Airport arrivalAirport, LocalDateTime localDeparture, LocalDateTime localArrival) {
        this.flightNumber = Objects.requireNonNull(flightNumber, "Номер рейса обязателен");
        this.departureAirport = Objects.requireNonNull(departureAirport, "Аэропорт вылета обязателен");
        this.arrivalAirport = Objects.requireNonNull(arrivalAirport, "Аэропорт прилёта обязателен");
        this.localDeparture = Objects.requireNonNull(localDeparture, "Время вылета обязательно");
        this.localArrival = Objects.requireNonNull(localArrival, "Время прилёта обязательно");
        if (!getDepartureInstant().isBefore(getArrivalInstant())) {
            throw new IllegalArgumentException("Момент прилёта должен быть позже момента вылета");
        }
    }
    @Override public String getId() {
        return flightNumber;
    }
    public String getFlightNumber() {
        return flightNumber;
    }
    public Airport getDepartureAirport() {
        return departureAirport;
    }
    public Airport getArrivalAirport() {
        return arrivalAirport;
    }
    // В Instant gj UTC
    public Instant getDepartureInstant() {
        return localDeparture.atZone(departureAirport.getZoneId()).toInstant();
    }
    public Instant getArrivalInstant() {
        return localArrival.atZone(arrivalAirport.getZoneId()).toInstant();
    }
    public Duration getDuration() {
        return Duration.between(getDepartureInstant(), getArrivalInstant());
    }
}