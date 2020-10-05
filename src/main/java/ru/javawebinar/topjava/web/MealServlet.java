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
import java.time.LocalTime;
import java.util.concurrent.BlockingQueue;
import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(name = "MealServlet")
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("method") != null && !request.getParameter("method").isEmpty()) {
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
