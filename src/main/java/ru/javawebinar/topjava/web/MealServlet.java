package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "MealServlet")
public class MealServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<MealTo> mealToList = MealsUtil.filteredByStreams(Meal.meals, LocalTime.MIN, LocalTime.MAX, 2000);

        request.setAttribute("mealToList", mealToList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/meals.jsp");
        requestDispatcher.forward(request, response);
    }
}
