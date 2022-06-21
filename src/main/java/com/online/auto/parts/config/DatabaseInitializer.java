package com.online.auto.parts.config;

import com.online.auto.parts.entity.AutoPart;
import com.online.auto.parts.entity.Role;
import com.online.auto.parts.entity.User;
import com.online.auto.parts.service.AutoPartService;
import com.online.auto.parts.service.RoleService;
import com.online.auto.parts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.online.auto.parts.entity.RoleType.*;

@Component
public class DatabaseInitializer {
    private UserService userService;
    private RoleService roleService;
    private AutoPartService autoPartService;

    @Autowired
    public DatabaseInitializer(UserService userService, RoleService roleService, AutoPartService autoPartService) {
        this.userService = userService;
        this.roleService = roleService;
        this.autoPartService = autoPartService;
    }
    @PostConstruct
    private void postConstruct() {
        // сохранение роли в БД
        Role sellerRole = SELLER.getRole();
        Role customerRole = CUSTOMER.getRole();
        roleService.save(sellerRole);
        roleService.save(customerRole);

        // сохранение админа в БД
        User seller = new User("Seller", "sell", "seller@mail.ru", "123");
        seller.addRole(sellerRole);
        userService.save(seller);

        User customer = new User("Customer", "cust", "customer@mail.ru", "123");
        customer.addRole(customerRole);
        userService.save(customer);

        // сохранение запчастей в БД
        AutoPart autoPart1 = new AutoPart("BOSCH 092 M06 180", 4515.0, "Accumulator", 3);
        AutoPart autoPart2 = new AutoPart("BOSCH 0 092 S40 040", 131.0, "Accumulator", 1);

        AutoPart autoPart3 = new AutoPart("NGK 5584", 594.0, "Spark plug", 3);
        AutoPart autoPart4 = new AutoPart("BOSCH 0 242 235 666", 145.0, "Spark plug", 6);

        AutoPart autoPart5 = new AutoPart("MONROE 23994", 4336.0, "Shock absorber", 5);
        AutoPart autoPart6 = new AutoPart("MONROE G7455", 6952.0, "Shock absorber", 3);

        AutoPart autoPart7 = new AutoPart("CALORSTAT BY VERNET TH1439.87J", 552.0, "Thermostat", 100);
        AutoPart autoPart8 = new AutoPart("CALORSTAT BY VERNET TH4898.92J", 579.0, "Thermostat", 3);
        AutoPart autoPart9 = new AutoPart("Dunlop Grandtrek PT3", 8690.00, "Wheels", 189);
        AutoPart autoPart10 = new AutoPart("Nokian Hakka Green 3", 4950.00, "Wheels", 154);
        AutoPart autoPart11 = new AutoPart("MICHELIN Pilot Sport 5", 10820.00, "Wheels", 156);

        AutoPart autoPart12 = new AutoPart("Video recorders XiaoMi Dash Cam 1S", 5999.00, "Video recorders", 200);

        autoPartService.save(autoPart1);
        autoPartService.save(autoPart2);
        autoPartService.save(autoPart3);
        autoPartService.save(autoPart4);
        autoPartService.save(autoPart5);
        autoPartService.save(autoPart6);
        autoPartService.save(autoPart7);
        autoPartService.save(autoPart8);
        autoPartService.save(autoPart9);
        autoPartService.save(autoPart10);
        autoPartService.save(autoPart11);
        autoPartService.save(autoPart12);
    }


}
