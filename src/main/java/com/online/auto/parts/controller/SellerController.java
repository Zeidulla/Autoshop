package com.online.auto.parts.controller;


import com.online.auto.parts.FileWorker;
import com.online.auto.parts.entity.AutoPart;
import com.online.auto.parts.entity.User;
import com.online.auto.parts.service.AutoPartService;
import com.online.auto.parts.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@Controller
public class SellerController {
    @Autowired
    private UserService userService;

    @Autowired
    private AutoPartService autoPartService;

    @Autowired
    private FileWorker fileWorker;

    @GetMapping("/seller")
    public String sellerMainPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());

        model.addAttribute("user", user);

        return "seller_main";
    }

    @GetMapping("/seller/add_part")
    public String addPart(Model model) {
        AutoPart autoPart = new AutoPart();

        model.addAttribute("part", autoPart);

        return "seller_add_part";
    }

    @PostMapping("/seller/add_part")
    public String addPart(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute AutoPart autoPart, @RequestParam("file") MultipartFile file) {
        User user = userService.findByUsername(userDetails.getUsername());

        user.addAutoPart(autoPart);

        String fileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());


        if (fileWorker.save(file, fileName)) {
            autoPart.setPathToPicture(fileName);
        }

        autoPartService.save(autoPart);

        return "redirect:/seller";
    }

    @GetMapping("/seller/delete/{part_id}")
    public String deletePartById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("part_id") Long partId) {
        autoPartService.deleteById(partId);

        return "redirect:/seller";
    }

    @GetMapping("/seller/update/{part_id}")
    public String updatePart(@PathVariable("part_id") Long partId, Model model) {
        AutoPart part = autoPartService.findAutoPartById(partId);

        model.addAttribute("part", part);

        return "seller_update_part";
    }

    @PostMapping("/seller/update_part/{part_id}")
    public String updatePartPost(@PathVariable("part_id") Long partId, @ModelAttribute AutoPart autoPart) {
        AutoPart updated = autoPartService.findAutoPartById(partId);

        updated.setName(autoPart.getName());
        updated.setPrice(autoPart.getPrice());
        updated.setCategory(autoPart.getCategory());
        updated.setTotalQuantity(autoPart.getTotalQuantity());

        autoPartService.update(updated);

        return "redirect:/seller";
    }

}
