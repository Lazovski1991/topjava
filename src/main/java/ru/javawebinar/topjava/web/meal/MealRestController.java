package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;

public class MealRestController {
    @Autowired
    private MealService service;


    public Meal save(Meal meal) {
        return service.save(meal);
    }

    public void delete(int id) {
        service.delete(id);
    }

    public Meal get(int id) {
        return service.get(id);
    }

    public Collection<Meal> getAll(Integer userId) {
        return service.getAll(SecurityUtil.authUserId());
    }
}