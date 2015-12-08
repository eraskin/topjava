package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.repository.InMemoryMealRepo;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealsServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MealsServlet.class);
    private static int MAX_PER_DAY = 2000;

    private MealRepository mealsRepo;

    @Override
    public void init(ServletConfig config) throws ServletException {
        mealsRepo = new InMemoryMealRepo();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealsList");

        request.setAttribute("meals", UserMealsUtil.getUserMealsWithExceeded(mealsRepo.getAllMeals(), MAX_PER_DAY));
        request.getRequestDispatcher("/mealsList.jsp").forward(request, response);
    }
}
