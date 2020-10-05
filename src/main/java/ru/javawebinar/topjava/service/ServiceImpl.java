package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

public class ServiceImpl implements Service {
    private static volatile long id = 0;

    @Override
    public void delete(Long id) {
        for (Meal pair : Meal.meals) {
            if (pair.getId() == id) {
                Meal.meals.remove(pair);
            }
        }
    }

    @Override
    public void add(Meal meal) {

    }

    @Override
    public void update() {

    }

    public static long getId() {
        id = id + 1;
        return id;
    }
}
