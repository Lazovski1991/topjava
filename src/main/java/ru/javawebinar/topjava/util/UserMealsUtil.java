package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        /*List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);*/

          System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> list = new ArrayList<>();

        Map<UserMeal, Integer> map = new HashMap<>();
        Map<Integer, UserMeal> map2 = new HashMap<>();
        for (UserMeal meal : meals) {
            map.put(new UserMeal(meal.getDateTime(), meal.getDescription(), meal.getCalories()), meal.getDateTime().getDayOfMonth());
        }

        map.forEach((key, value) -> map2.merge(value, key, (v1, v2) ->
                new UserMeal(v1.getDateTime(), v1.getDescription(), (v1.getCalories() + v2.getCalories()))));

        for (Map.Entry<UserMeal, Integer> pair : map.entrySet()) {
            Boolean excess = false;
            if (map2.get(pair.getKey().getDateTime().getDayOfMonth()).getCalories() > caloriesPerDay) {
                excess = true;
            }
            list.add(new UserMealWithExcess(pair.getKey().getDateTime(), pair.getKey().getDescription(), pair.getKey().getCalories(), excess));
        }

        return list;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> list = new ArrayList<>();
        Map<UserMeal, Integer> map = new HashMap<>();
        Map<Integer, UserMeal> map2 = new HashMap<>();

        meals.forEach(meal ->
                map.put(new UserMeal(meal.getDateTime(), meal.getDescription(), meal.getCalories()), meal.getDateTime().getDayOfMonth()));


        map.forEach((key, value) -> map2.merge(value, key, (v1, v2) ->
                new UserMeal(v1.getDateTime(), v1.getDescription(), (v1.getCalories() + v2.getCalories()))));

        map.forEach((k, v) -> {
            Boolean excess = false;
            if (map2.get(k.getDateTime().getDayOfMonth()).getCalories() > caloriesPerDay) {
                excess = true;
            }
            list.add(new UserMealWithExcess(k.getDateTime(), k.getDescription(), k.getCalories(), excess));
        });

        return list;
    }
}