package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll() {
        log.info("get all meals for user {}", authUserId());
        return MealsUtil.getTos(service.getAll(authUserId()), DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(int id) {
        log.info("get meal with id {}", id);
        return service.get(id, authUserId());
    }

    public Meal create(Meal meal) {
        log.info("create meal {}", meal);
        return service.create(meal, authUserId());
    }
    public void update(Meal meal) {
        log.info("update meal with id {}", meal.getId());
        service.update(meal, authUserId());
    }
    public void delete(int id) {
        log.info("delete meal with id {}", id);
        service.delete(id, authUserId());
    }

}