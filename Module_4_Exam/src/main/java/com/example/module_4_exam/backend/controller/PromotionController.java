package com.example.module_4_exam.backend.controller;

import com.example.module_4_exam.backend.model.PromotionDto;
import com.example.module_4_exam.backend.service.PromotionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
public class PromotionController {

    @Autowired
    private PromotionService service;

    @GetMapping("/")
    public String listPromotions(Model model) {
        model.addAttribute("promotions", service.getAll());
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("promotionDto", new PromotionDto());
        return "form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("promotionDto") PromotionDto promotionDto,
                       BindingResult result, Model model) {
        if (promotionDto.getEndDate().isBefore(promotionDto.getStartDate().plusDays(1))) {
            result.rejectValue("endDate", "error.endDate", "Ngày kết thúc phải sau ngày bắt đầu ít nhất 1 ngày");
        }

        if (result.hasErrors()) {
            return "form";
        }

        service.save(promotionDto);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/?deleted=true";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) Long discount,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                         Model model) {
        model.addAttribute("promotions", service.search(discount, startDate, endDate));
        return "index";
    }
}