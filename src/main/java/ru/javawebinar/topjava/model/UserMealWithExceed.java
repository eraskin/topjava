package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMealWithExceed {
    protected final LocalDateTime dateTime;

    protected final String description;

    protected final int calories;

    protected final boolean exceed;



    public UserMealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String toString() {
        String exceedMessage = exceed ? " Ты слишком много съел в этот день" : "Как ты выдержал?! Удивительно!";
        return description + " with " + calories + " calories from " + dateTime.format(TimeUtil.dateTimeFormatter) + " " + exceedMessage;
    }
}
