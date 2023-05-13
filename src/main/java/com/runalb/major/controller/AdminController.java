package com.runalb.major.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String adminPage(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String categoriesPage(){
        return "categories";
    }

    @GetMapping("admin/categories/add")
    public String addCategoriesPage(){
        return "categoriesAdd";
    }
}
