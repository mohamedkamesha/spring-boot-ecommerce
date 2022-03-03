package com.kamesha.ecommerce.service;

import com.kamesha.ecommerce.dto.Purchase;
import com.kamesha.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
