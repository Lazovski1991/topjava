package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.ServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.BlockingQueue;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(name = "MealServlet")
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null && !req.getParameter("id").isEmpty()) {
            ServiceImpl service = new ServiceImpl();
            Long id = Long.parseLong(req.getParameter("id"));
            String description = req.getParameter("description");
            LocalDateTime date = LocalDateTime.parse(req.getParameter("date"));
            int calories = Integer.parseInt(req.getParameter("calories"));
            Meal meal = new Meal(id, date, description, calories);
            service.update(meal);
            log.debug("redirect to meals");
            resp.sendRedirect("/topjava/meals");
        } else {
            ServiceImpl service = new ServiceImpl();
            String description = req.getParameter("description");
            LocalDateTime date = LocalDateTime.parse(req.getParameter("date"));
            int calories = Integer.parseInt(req.getParameter("calories"));
            Meal meal = new Meal(ServiceImpl.getId(), date, description, calories);
            service.add(meal);
            resp.sendRedirect("/topjava/meals");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
            ServiceImpl service = new ServiceImpl();
            service.delete(Long.parseLong(request.getParameter("id")));
            log.debug("redirect to meals");
            response.sendRedirect("/topjava/meals");
        } else {
            BlockingQueue<MealTo> mealToList = MealsUtil.filteredByStreams(Meal.meals, LocalTime.MIN, LocalTime.MAX, 2000);
            request.setAttribute("mealToList", mealToList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/meals.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
