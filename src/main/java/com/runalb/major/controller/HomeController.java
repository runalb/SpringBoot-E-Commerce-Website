package com.runalb.major.controller;

import com.runalb.major.service.CategoryService;
import com.runalb.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping({"/","/home"})
    public String homePage(Model model){
        return "index";
    }


    @GetMapping("/shop")
    public String shopPage(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.getAllProduct());
        return "shop";
    }


    @GetMapping("/shop/category/{id}")
    public String shopByCategoryPage(@PathVariable int id, Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("products",productService.getAllProductsByCategoryId(id));
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProductPage(@PathVariable int id, Model model){
        model.addAttribute("product",productService.getProductById(id).get());
        return "viewProduct";
    }

}
