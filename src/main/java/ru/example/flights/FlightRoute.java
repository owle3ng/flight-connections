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
                throw new IllegalArgumentException("Аэропорты не совпадают");
            }
            Duration connection = Duration.between(previous.getArrivalInstant(), next.getDepartureInstant());

            if (connection.isNegative()) {
                throw new ConnectionException("Слишком короткая пересадка между рейсами " + previous.getFlightNumber() + " и " + next.getFlightNumber());
            }
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
    public String printRoute() {
        StringBuilder result = new StringBuilder("Маршрут:\n");
        for (int i = 0; i < segments.size(); i++) {
            result.append(i + 1).append(". ").append(segments.get(i).flight().describe()).append('\n');
            if (i + 1 < segments.size()) {
                Flight current = segments.get(i).flight();
                Flight next = segments.get(i + 1).flight();
                Duration wait = Duration.between(current.getArrivalInstant(), next.getDepartureInstant());
                result.append("   Пересадка в ").append(current.getArrivalAirport().getIataCode())
                        .append(": ").append(Flight.formatDuration(wait)).append('\n');
            }
        }
        result.append("Время в самолёте: ").append(Flight.formatDuration(getTotalFlightDuration())).append('\n');
        result.append("Время на пересадки: ").append(Flight.formatDuration(getTotalConnectionDuration())).append('\n');
        result.append("Полное время в пути: ").append(Flight.formatDuration(getTotalTravelDuration()));
        return result.toString();
    }
}
