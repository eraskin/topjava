package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 09.12.2015.
 */
public class InMemoryMealRepo implements MealRepository {

    private static Map<Integer, UserMeal> mealsMap = new HashMap<>();

    static {
        mealsMap.put(1, new UserMeal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        mealsMap.put(2, new UserMeal(2, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        mealsMap.put(3, new UserMeal(3, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        mealsMap.put(4, new UserMeal(4, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        mealsMap.put(5, new UserMeal(5, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        mealsMap.put(6, new UserMeal(6, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public Map<Integer, UserMeal> getAllMeals() {
        return mealsMap;
    }
}
