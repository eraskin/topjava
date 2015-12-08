package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 09.12.2015.
 */
public interface MealRepository {

    Map<Integer, UserMeal> getAllMeals();
}
