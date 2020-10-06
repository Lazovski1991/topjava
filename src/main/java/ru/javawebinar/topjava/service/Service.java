package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

public interface Service {
    void delete(Long id);

    void add(Meal meal);

    void update(Meal meal);
}
