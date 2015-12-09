package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by user on 09.12.2015.
 */
public class InMemoryMealRepo implements MealRepository {

    private AtomicInteger id = new AtomicInteger(0);
    private Map<Integer, UserMeal> mealsMap = new ConcurrentHashMap<>();

    {
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        save(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public Collection<UserMeal> getAll() {
        return mealsMap.values();
    }

    @Override
    public void delete(int id) {
        mealsMap.remove(id);
    }

    @Override
    public UserMeal get(int id) {
        return mealsMap.get(id);
    }

    @Override
    public void save(UserMeal meal) {
        if(meal.getId() == null) {
            meal.setId(id.incrementAndGet());
        }
        mealsMap.put(meal.getId(), meal);
    }
}
