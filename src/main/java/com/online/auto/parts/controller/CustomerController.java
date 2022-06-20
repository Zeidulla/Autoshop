package com.online.auto.parts.controller;

import com.online.auto.parts.FileWorker;
import com.online.auto.parts.entity.*;
import com.online.auto.parts.model.SearchForm;
import com.online.auto.parts.service.AutoPartService;
import com.online.auto.parts.service.OrderPositionService;
import com.online.auto.parts.service.OrderService;
import com.online.auto.parts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class CustomerController {
    @Autowired
    private UserService userService;

    @Autowired
    private AutoPartService autoPartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderPositionService orderPositionService;

    @Autowired
    private FileWorker fileWorker;

    @GetMapping("/customer")
    public String customerMainPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        List<AutoPart> autoParts = autoPartService.findAll();
        Set<String> categories = autoParts.stream().map(AutoPart::getCategory).collect(Collectors.toSet());
        SearchForm searchForm = new SearchForm();
        searchForm.setCategories(categories);

        model.addAttribute("search", searchForm);
        model.addAttribute("parts", autoParts);
        model.addAttribute("user", user);

        return "customer_main";
    }

    @GetMapping("/customer/add_to_card/{part_id}")
    public String addToCart(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("part_id") Long partId) {
        User user = userService.findByUsername(userDetails.getUsername());

        Order order = null;
        List<Order> orders = orderService.findOrderByOrderStatusAndUser(OrderStatus.NEW, user);

        if (!orders.isEmpty()) {
            order = orders.get(0);
        }


        if (order == null) {
            order = new Order();
        }

        AutoPart autoPart = autoPartService.findAutoPartById(partId);
        if (autoPart.getTotalQuantity() > 0) {
            OrderPosition orderPosition = new OrderPosition(1, autoPart);

            order.setUser(user);
            order.addOrderPosition(orderPosition);

            orderService.save(order);
            orderPositionService.save(orderPosition);
        }

        return "redirect:/customer";
    }

    @GetMapping("/customer/cart")
    public String showCart(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());

        Order order = null;
        List<Order> orders = orderService.findOrderByOrderStatusAndUser(OrderStatus.NEW, user);

        if (!orders.isEmpty()) {
            order = orders.get(0);
        }

        if (order == null) {
            order = new Order();
        }

        Set<OrderPosition> orderPositions = order.getPositions();

        double price = orderPositions.stream().map(s -> s.getQuantity() * s.getAutoPart().getPrice()).reduce(0.0, (a, b) -> a + b);

        model.addAttribute("positions", orderPositions);
        model.addAttribute("price", price);

        return "customer_cart";
    }

    @GetMapping("/customer/position/inc/{position_id}")
    public String increment(@PathVariable("position_id") Long positionId) {
        OrderPosition orderPosition = orderPositionService.findById(positionId);

        int partQuantity = orderPosition.getQuantity();

        if (partQuantity + 1 <= orderPosition.getAutoPart().getTotalQuantity()) {
            orderPosition.setQuantity(partQuantity + 1);
            orderPositionService.save(orderPosition);
        }

        return "redirect:/customer/cart";
    }

    @GetMapping("/customer/position/dec/{position_id}")
    public String decrement(@PathVariable("position_id") Long positionId) {
        OrderPosition orderPosition = orderPositionService.findById(positionId);

        int partQuantity = orderPosition.getQuantity();

        if (partQuantity - 1 > 0) {
            orderPosition.setQuantity(partQuantity - 1);
            orderPositionService.save(orderPosition);
        }

        return "redirect:/customer/cart";
    }

    @GetMapping("/customer/position/delete/{position_id}")
    public String delete(@PathVariable("position_id") Long id) {
        orderPositionService.remove(id);

        return "redirect:/customer/cart";
    }

    @GetMapping("/customer/checkout")
    public String checkout(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        Order order = null;
        List<Order> orders = orderService.findOrderByOrderStatusAndUser(OrderStatus.NEW, user);

        if (!orders.isEmpty()) {
            order = orders.get(0);
        }

        if (order == null || order.getPositions().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }


        for (OrderPosition position : order.getPositions()) {
            int checkoutQuantity = position.getQuantity();
            int totalQuantity = position.getAutoPart().getTotalQuantity();

            AutoPart checkoutPart = position.getAutoPart();

            checkoutPart.setTotalQuantity(Math.max(totalQuantity - checkoutQuantity, 0));

            autoPartService.save(checkoutPart);
        }

        order.setStatus(OrderStatus.COMPLETED);
        order.setUpdatingDate(LocalDateTime.now());

        orderService.save(order);

        Order newOrder = new Order();

        orderService.save(newOrder);

        return "customer_success_checkout";
    }


    @GetMapping("/customer/history")
    public String showHistory(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        List<Order> orders = orderService.findOrderByOrderStatusAndUser(OrderStatus.COMPLETED, user);

        model.addAttribute("orders", orders);

        return "customer_history";
    }


    @PostMapping("/customer/search")
    public String filter(Model model, @AuthenticationPrincipal UserDetails userDetails, @ModelAttribute SearchForm searchForm) {
        User user = userService.findByUsername(userDetails.getUsername());
        List<AutoPart> autoParts = autoPartService.findAll();
        Set<String> categories = autoParts.stream().map(AutoPart::getCategory).collect(Collectors.toSet());
        SearchForm nextSearch = new SearchForm();
        nextSearch.setCategories(categories);

        List<AutoPart> filteredAutoParts = autoParts.stream().filter(c -> {
            boolean searchKeyBool = c.getName().toLowerCase().contains(searchForm.getSearchKey().toLowerCase());
            boolean categoryBool = c.getCategory().contains(searchForm.getSelected());
            if (searchForm.getSearchKey().isEmpty()) {
                return categoryBool;
            } else if (searchForm.getCategories().isEmpty()) {
                return searchKeyBool;
            }
            return categoryBool && searchKeyBool;
        }).collect(Collectors.toList());

        model.addAttribute("search", nextSearch);
        model.addAttribute("parts", filteredAutoParts);
        model.addAttribute("user", user);

        return "customer_main";
    }

    @GetMapping("/customer/part_info/{part_id}")
    public String showPartInfo(Model model, @PathVariable("part_id") Long id) {
        AutoPart autoPart = autoPartService.findAutoPartById(id);

        model.addAttribute("part", autoPart);

        if (autoPart.getPathToPicture() == null) {
            autoPart.setPathToPicture("not_found.png");
        }

        model.addAttribute("pic_path", fileWorker.absoluteFilePath(autoPart.getPathToPicture()));

        return "customer_part_info";
    }


}
