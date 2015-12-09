package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Map;

/**
 * Created by user on 09.12.2015.
 */
public interface MealRepository {

    Map<Integer, UserMeal> getAll();
    void delete(int id);
    UserMeal get(int id);
    void save(UserMeal meal);
}
