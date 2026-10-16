package ru.example.flights;
import java.time.Duration;
import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) {
        Airport ams = new Airport("AMS", "Амстердам Схипхол", ZoneId.of("Europe/Amsterdam"));
        Airport fra = new Airport("FRA", "Франкфурт", ZoneId.of("Europe/Berlin"));
        Airport hel = new Airport("HEL", "Хельсинки", ZoneId.of("Europe/Helsinki"));
        Airport jfk = new Airport("JFK", "Нью-Йорк имени Кеннеди", ZoneId.of("America/New_York"));
        Airport lhr = new Airport("LHR", "Лондон Хитроу", ZoneId.of("Europe/London"));
        Airport cdg = new Airport("CDG", "Париж Шарль-де-Голль", ZoneId.of("Europe/Paris"));
        Airport mad = new Airport("MAD", "Мадрид Барахас", ZoneId.of("Europe/Madrid"));
        Airport fco = new Airport("FCO", "Рим Фьюмичино", ZoneId.of("Europe/Rome"));
        Airport waw = new Airport("WAW", "Варшава Шопен", ZoneId.of("Europe/Warsaw"));
        Airport vie = new Airport("VIE", "Вена", ZoneId.of("Europe/Vienna"));
        Airport prg = new Airport("PRG", "Прага", ZoneId.of("Europe/Prague"));

        List<Airport> airports = List.of(ams, fra, hel, jfk, lhr, cdg, mad, fco, waw, vie, prg);
        System.out.println("Мои Аэропорты");
        airports.forEach(a -> System.out.println(a.getId() + " | " + a.getName() + " | " + a.getZoneId()));


        Flight f1 = new Flight("KL100", ams, fra,
                LocalDateTime.of(2026, 11, 10, 8, 0), LocalDateTime.of(2026, 11, 10, 9, 15));
        Flight f2 = new Flight("LH200", fra, hel,
                LocalDateTime.of(2026, 11, 10, 11, 0), LocalDateTime.of(2026, 11, 10, 14, 20));
        Flight f3 = new Flight("AY300", hel, lhr,
                LocalDateTime.of(2026, 11, 10, 16, 0), LocalDateTime.of(2026, 11, 10, 17, 10));
        Flight f4 = new Flight("BA400", lhr, cdg,
                LocalDateTime.of(2026, 11, 11, 7, 0), LocalDateTime.of(2026, 11, 11, 9, 20));
        Flight f5 = new Flight("AF500", cdg, mad,
                LocalDateTime.of(2026, 11, 11, 11, 0), LocalDateTime.of(2026, 11, 11, 13, 10));
        Flight f6 = new Flight("IB600", mad, fco,
                LocalDateTime.of(2026, 11, 11, 15, 0), LocalDateTime.of(2026, 11, 11, 17, 25));
        Flight f7 = new Flight("AZ700", fco, waw,
                LocalDateTime.of(2026, 11, 12, 8, 0), LocalDateTime.of(2026, 11, 12, 10, 20));
        Flight f8 = new Flight("LO800", waw, vie,
                LocalDateTime.of(2026, 11, 12, 12, 0), LocalDateTime.of(2026, 11, 12, 13, 15));
        Flight f9 = new Flight("OS900", vie, prg,
                LocalDateTime.of(2026, 11, 12, 15, 0), LocalDateTime.of(2026, 11, 12, 16, 0));
        Flight f10 = new Flight("OK010", prg, ams,
                LocalDateTime.of(2026, 11, 13, 9, 0), LocalDateTime.of(2026, 11, 13, 10, 35));

        List<Flight> flights = List.of(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10);
        System.out.println("Длительность рейсов:");
        flights.forEach(f -> System.out.println(f));
        System.out.println("\nС пересадками:");
        FlightRoute route = new FlightRoute(
                List.of(
                        new FlightSegment(f1),
                        new FlightSegment(f2),
                        new FlightSegment(f3)
                ),
                Duration.ofMinutes(45)
        );
        System.out.println("Минимальное время пересадки: " + route.getMinimumConnectionTime().toMinutes() + " минут");
        System.out.println(route.printRoute());
    }
}