package com.runalb.major.controller;

import com.runalb.major.dto.ProductDTO;
import com.runalb.major.model.Category;
import com.runalb.major.model.Product;
import com.runalb.major.service.CategoryService;
import com.runalb.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String adminPage(){
        return "adminHome";
    }


    // Categories Section

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


    // Product Section

    @GetMapping("/admin/products")
    public String getProductsPage(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "products";
    }


    @GetMapping("/admin/products/add")
    public String getAddProductPage(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }


    @PostMapping("/admin/products/add")
    public String postAddProduct(@ModelAttribute("productDTO") ProductDTO productDTO,
                                 @RequestParam("productImage") MultipartFile file,
                                 @RequestParam("imgName") String imgName) throws IOException {

        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());

        // Img
        String imageUUID;

        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir,imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);

        productService.addProduct(product);
        return "redirect:/admin/products";
    }


    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable int id, Model model){
        Product product = productService.getProductById(id).get();

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId((product.getCategory().getId()));
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());

        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productDTO", productDTO);
        return "/productsAdd";
    }


}
