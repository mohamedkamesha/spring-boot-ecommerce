package com.kamesha.ecommerce.dto;

import com.kamesha.ecommerce.model.Address;
import com.kamesha.ecommerce.model.Customer;
import com.kamesha.ecommerce.model.Order;
import com.kamesha.ecommerce.model.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address billingAddress;
    private Address shippingAddress;
    private Order order;
    private Set<OrderItem> orderItems ;
}
