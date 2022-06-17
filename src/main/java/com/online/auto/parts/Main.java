package com.online.auto.parts;

import com.online.auto.parts.entity.AutoPart;
import com.online.auto.parts.entity.Order;
import com.online.auto.parts.entity.OrderPosition;
import com.online.auto.parts.entity.ShoppingCart;
import com.online.auto.parts.service.AutoPartService;
import com.online.auto.parts.service.OrderPositionService;
import com.online.auto.parts.service.OrderService;
import com.online.auto.parts.service.ShoppingCartService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import static com.online.auto.parts.entity.OrderStatus.NEW;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);

        var orderService = context.getBean(OrderService.class);
        var autoPartService = context.getBean(AutoPartService.class);
        var cartService = context.getBean(ShoppingCartService.class);
        var orderPositionService = context.getBean(OrderPositionService.class);

        // создаем запчасть
        var autoPart = new AutoPart("Video recorders XiaoMi Dash Cam 1S", 5999.00, "Video recorders", 200);

        // создаем позицию в заказе и добавляем в Autopart
        var orderPosition = new OrderPosition(2, autoPart);

        // создаем заказ
        var order = new Order(NEW, LocalDateTime.now().minusDays(1), LocalDateTime.now());

        // добавляем в заказ позицию
        order.addOrderPosition(orderPosition);

        // создаем корзину с заказом
        var cart = new ShoppingCart(order);


        autoPartService.save(autoPart);
        cartService.save(cart);
        orderPositionService.save(orderPosition);
        cartService.save(cart);
    }

}
