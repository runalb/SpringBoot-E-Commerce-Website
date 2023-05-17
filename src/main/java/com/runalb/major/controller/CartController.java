package com.runalb.major.controller;

import com.runalb.major.global.GlobalData;
import com.runalb.major.model.Order;
import com.runalb.major.model.Product;
import com.runalb.major.service.OrderService;
import com.runalb.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {
    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        GlobalData.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }

    @GetMapping("/cart")
    public String getCartPage(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        return "cart";
    }


    @GetMapping("/cart/removeItem/{index}")
    public String removeCartItem(@PathVariable int index){
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }


    @GetMapping("/checkout")
    public String getCheckoutPage(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("order", new Order());
        return "checkout";
    }

    @PostMapping("/checkout")
    public String postCheckout(@ModelAttribute("order") Order order){
        order.setProducts(GlobalData.cart);
        order.setTotalAmount(GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        orderService.addOrder(order);
        GlobalData.cart.clear();
        return "redirect:viewOrder/"+ order.getId();
    }

    @GetMapping("/viewOrder/{id}")
    public String getViewOrderPage(@PathVariable int id, Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("order", orderService.getOrderById(id).get());
        return "viewOrder";
    }



}
