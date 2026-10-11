package ru.example.flights;

import java.util.Objects;


public final class Flight implements Identifiable {
    private final String flightNumber;
    private final Airport departureAirport;
    private final Airport arrivalAirport;

    public Flight(String flightNumber, Airport departureAirport, Airport arrivalAirport, LocalDateTime, LocalDateTime) {
        this.flightNumber = Objects.requireNonNull(flightNumber, "Номер рейса обязателен");
        this.departureAirport = Objects.requireNonNull(departureAirport, "Аэропорт вылета обязателен");
        this.arrivalAirport = Objects.requireNonNull(arrivalAirport, "Аэропорт прилёта обязателен");
    }