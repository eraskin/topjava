package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.InMemoryMealRepo;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealsServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MealsServlet.class);
    private static int MAX_PER_DAY = 2000;
    private static String ACTION_PARAMETER = "action";
    private static String ACTION_DELETE = "delete";

    private MealRepository mealsRepo;

    @Override
    public void init(ServletConfig config) throws ServletException {
        mealsRepo = new InMemoryMealRepo();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealsList");
        String action = request.getParameter(ACTION_PARAMETER);

        if(action == null) {
            LOG.info("get all meals from repo");
            request.setAttribute("meals", UserMealsUtil.getUserMealsWithExceeded(mealsRepo.getAll(), MAX_PER_DAY));
            request.getRequestDispatcher("/mealsList.jsp").forward(request, response);
        } else if(action.equals(ACTION_DELETE)) {
            Integer id = Integer.valueOf(request.getParameter("id"));
            mealsRepo.delete(id);
            LOG.info("Meal with id " + id + " was deleted");
            response.sendRedirect("meals");
        } else {

            UserMeal meal;
            if(action.equals("create")) {
                meal = new UserMeal(LocalDateTime.now(), "Введи название", 100);
            } else {
                meal = mealsRepo.get(Integer.valueOf(request.getParameter("id")));
            }

            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealsEdit.jsp").forward(request, response);

        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            String id = request.getParameter("id");
            UserMeal userMeal = new UserMeal(
                            LocalDateTime.parse(request.getParameter("dateTime")),
                            request.getParameter("description"),
                            Integer.valueOf(request.getParameter("calories")));

            if(!id.isEmpty()) {
                userMeal.setId(Integer.valueOf(id));
            }

            LOG.info(userMeal.getId() == null ? "Create" : "Update", userMeal);
            mealsRepo.save(userMeal);
            response.sendRedirect("meals");
    }
}
