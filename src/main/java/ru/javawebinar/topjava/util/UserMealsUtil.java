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

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
             mealsTo.forEach(System.out::println);

        //  System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> list = new ArrayList<>();

        Map<UserMeal, Integer> map = new HashMap<>(); //date, calories
        Map<Integer, UserMeal> map2 = new HashMap<>(); //date, sumCalories
        for (UserMeal meal : meals) {
            map.put(new UserMeal(meal.getDateTime(), meal.getDescription(), meal.getCalories()), meal.getDateTime().getDayOfMonth());
        }

        map.forEach((key, value) -> map2.merge(value, key, (v1, v2) ->
                new UserMeal(v1.getDateTime(), v1.getDescription(), (v1.getCalories() + v2.getCalories()))));

        for (Map.Entry<UserMeal,Integer> pair : map.entrySet()) {
            Boolean excess = false;
            if (map2.get(pair.getKey().getDateTime().getDayOfMonth()).getCalories() > caloriesPerDay) {
                excess = true;
            }
            list.add(new UserMealWithExcess(pair.getKey().getDateTime(), pair.getKey().getDescription(), pair.getKey().getCalories(), excess));
        }

        return list;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        List<UserMealWithExcess> userMealWithExcesses = new ArrayList<>();
        Stream<UserMeal> filterStr = null;
        Map<List<UserMeal>, Boolean> map = new HashMap<>();

        for (Integer myInt : days) {
            filterStr = meals.stream().filter((pair) -> (pair.getDateTime().getDayOfMonth() == myInt));

            List<UserMeal> list = filterStr.collect(Collectors.toList());
            int summ = list.stream()
                    .map(UserMeal::getCalories)
                    .reduce((s1, s2) -> s1 + s2)
                    .orElse(0);

            boolean excess = false;
            if (summ > caloriesPerDay) excess = true;
            map.put(list, excess);
        }

        map.forEach((k, v) ->
                meals.forEach(meal ->
                        userMealWithExcesses.add(new UserMealWithExcess(meal.getDateTime(),
                                meal.getDescription(), meal.getCalories(), v))));

        return userMealWithExcesses;
    }
}
