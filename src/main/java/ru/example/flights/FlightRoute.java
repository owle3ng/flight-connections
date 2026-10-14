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
    public List<FlightSegment> getSegments() {
        return segments;
    }

    public Duration getMinimumConnectionTime() {
        return minimumConnectionTime;
    }

    public Duration getTotalFlightDuration() {
        return segments.stream().map(s -> s.flight().getDuration()).reduce(Duration.ZERO, Duration::plus);
    }

    public Duration getTotalConnectionDuration() {
        Duration total = Duration.ZERO;
        for (int i = 1; i < segments.size(); i++) {
            Flight previous = segments.get(i - 1).flight();
            Flight next = segments.get(i).flight();
            total = total.plus(Duration.between(previous.getArrivalInstant(), next.getDepartureInstant()));
        }
        return total;
    }

    public Duration getTotalTravelDuration() {
        Flight first = segments.getFirst().flight();
        Flight last = segments.getLast().flight();
        return Duration.between(first.getDepartureInstant(), last.getArrivalInstant());
    }