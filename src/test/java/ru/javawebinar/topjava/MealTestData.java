package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int ADMIN_MEAL_ID = START_SEQ + 2;
    public static final int USER_MEAL_ID = START_SEQ + 3;
    public static final int NOT_MEAL_ID = START_SEQ + 10;

    public static final Meal ADMIN_MEAL = new Meal(ADMIN_MEAL_ID, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0)
            , "Админ ланч", 510);

    public static final Meal USER_MEAL = new Meal(USER_MEAL_ID,
            LocalDateTime.of(2015, Month.MAY, 1, 14, 0)
            , "Юзер ланч", 710);

    public static final List<Meal> MEALS = Arrays.asList(USER_MEAL);

    public static final Meal UPDATED_USER_MEAL = new Meal(USER_MEAL_ID,
            LocalDateTime.of(2015, Month.MAY, 1, 14, 0)
            , "Юзер ланч", 1111);

    public static final Meal NEW_USER_MEAL = new Meal(LocalDateTime.of(2020, Month.MAY, 1, 14, 0)
            , "Юзер new", 1010);

    public static final LocalDate START = LocalDate.of(2020, Month.DECEMBER, 1);

    public static final LocalDate FINAL = LocalDate.of(2021, Month.DECEMBER, 1);

    public static final List<Meal> FILTER_MEALS = Arrays.asList();

    public static Meal getUpdate() {
        Meal updated = USER_MEAL;
        updated.setCalories(1111);
        return updated;
    }
}