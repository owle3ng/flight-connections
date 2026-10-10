package ru.example.flights;
import java.time.ZoneId;
import java.util.List;

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
        System.out.println("Аэропорты");
        airports.forEach(a -> System.out.println(a.getId() + " | " + a.getName() + " | " + a.getZoneId()));
    }
}