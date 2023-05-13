package com.runalb.major.controller;

import com.runalb.major.model.Category;
import com.runalb.major.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/admin")
    public String adminPage(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategoriesPage(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }

    @GetMapping("admin/categories/add")
    public String getAddCategoriesPage(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }


    @PostMapping("admin/categories/add")
    public String postAddCategories(@ModelAttribute("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
}
