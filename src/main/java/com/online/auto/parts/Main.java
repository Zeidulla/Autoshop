package com.online.auto.parts;

import com.online.auto.parts.entity.*;
import com.online.auto.parts.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;

import static com.online.auto.parts.entity.OrderStatus.CANCELED;
import static com.online.auto.parts.entity.OrderStatus.NEW;
import static com.online.auto.parts.entity.RoleType.*;

/**
 * 1. У пользователя:
 * главная страница
 * поиск товаров
 * корзина
 * список всех покупок
 * 2. У администратора:
 * создать/редактировать/удалить товар
 * редактировать пользователя
 * в профиле покупателя добавить историю покупок
 */
@SpringBootApplication
public class Main {
    /**
     * 1. Не зареганые пользователи скидывались на главную страницу, либо на страницу регистрации
     * 2. Не добавлять дублирующие записи
     * 3. Добавить поиск
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
