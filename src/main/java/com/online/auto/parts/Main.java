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

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
