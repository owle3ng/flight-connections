package ru.example.flights;

import java.util.Objects;

/** Сегмент маршрута, ссылающийся на конкретный рейс. */
public record FlightSegment(Flight flight) {
    public FlightSegment {
        Objects.requireNonNull(flight, "Рейс обязателен");
    }
}
