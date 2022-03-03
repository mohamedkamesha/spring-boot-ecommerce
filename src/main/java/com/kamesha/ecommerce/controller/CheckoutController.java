package com.kamesha.ecommerce.controller;


import com.kamesha.ecommerce.dto.Purchase;
import com.kamesha.ecommerce.dto.PurchaseResponse;
import com.kamesha.ecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

     private CheckoutService checkoutService;

     @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase){

       // System.out.println("hello");
         PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

         return purchaseResponse;
    }



}
