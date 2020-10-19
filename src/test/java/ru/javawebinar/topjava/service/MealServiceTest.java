package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"

})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService mealService;

    @Test
    public void get() {
        Meal meal = mealService.get(USER_MEAL_ID, USER_ID);
        Assert.assertEquals(USER_MEAL.getId(), meal.getId());
    }

    @Test
    public void delete() {
        mealService.delete(USER_MEAL_ID, USER_ID);
        Assert.assertThrows(NotFoundException.class, () -> mealService.get(USER_MEAL_ID, USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> filteredList = mealService.getBetweenInclusive(START, FINAL, USER_ID);
        Assert.assertArrayEquals(filteredList.toArray(), FILTER_MEALS.toArray());
    }

    @Test
    public void getAll() {
        List<Meal> list = mealService.getAll(USER_ID);
        Assert.assertArrayEquals(list.toArray(), MEALS.toArray());
    }

    @Test
    public void update() {
        mealService.update(getUpdate(), USER_ID);
        Assert.assertEquals(UPDATED_USER_MEAL, mealService.get(USER_MEAL_ID, USER_ID));
    }

    @Test
    public void create() {
        mealService.create(NEW_USER_MEAL, USER_ID);
        Assert.assertEquals(mealService.get(USER_MEAL_ID + 1, USER_ID), NEW_USER_MEAL);
    }

    @Test(expected = NotFoundException.class)
    public void deleteStrangerId() {
        mealService.delete(USER_MEAL_ID, ADMIN_ID);
    }
}