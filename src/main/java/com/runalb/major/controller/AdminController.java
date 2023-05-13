package com.runalb.major.controller;

import com.runalb.major.model.Category;
import com.runalb.major.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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


    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategories(@PathVariable int id){
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }


    @GetMapping("/admin/categories/update/{id}")
    public String updateCategories(@PathVariable int id, Model model){
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()){
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        } else {
            return "404";
        }
    }

}
