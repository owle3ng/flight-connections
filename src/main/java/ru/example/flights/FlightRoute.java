package ru.example.flights;

import java.time.Duration;
import java.util.List;

public final class FlightRoute {
    private final List<FlightSegment> segments;
    private final Duration minimumConnectionTime;

    public FlightRoute(List<FlightSegment> segments, Duration minimumConnectionTime) {
        this.minimumConnectionTime = minimumConnectionTime;
        if (minimumConnectionTime.isNegative()) {
            throw new IllegalArgumentException("Минимальное время пересадки не может быть <0");
        }
        if (segments.isEmpty()) {
            throw new IllegalArgumentException("Маршрут не может быть пустым");
        }
        this.segments = List.copyOf(segments);
        validateConnections();
    }
    private void validateConnections() {
        for (int i = 1; i < segments.size(); i++) {
            Flight previous = segments.get(i - 1).flight();
            Flight next = segments.get(i).flight();
            if (!previous.getArrivalAirport().equals(next.getDepartureAirport())) {
                throw new ConnectionException("Аэропорт стыковки неверный" + previous.getFlightNumber()+ "и "+ next.getFlightNumber());
            }
            Duration connection = Duration.between(previous.getArrivalInstant(), next.getDepartureInstant());
        }
    }