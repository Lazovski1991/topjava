package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.service.ServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Meal {
    public static BlockingQueue<Meal> meals =new LinkedBlockingDeque<>(
            Arrays.asList(
            new Meal(ServiceImpl.getId(),LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(ServiceImpl.getId(),LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(ServiceImpl.getId(),LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(ServiceImpl.getId(),LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(ServiceImpl.getId(),LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(ServiceImpl.getId(),LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(ServiceImpl.getId(),LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)));


    private Long id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public Meal(Long id, LocalDateTime dateTime, String description, int calories) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }


    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public Long getId() {
        return id;
    }
}
