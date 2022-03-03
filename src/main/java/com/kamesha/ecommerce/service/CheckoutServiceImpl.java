package com.kamesha.ecommerce.service;

import com.kamesha.ecommerce.dto.Purchase;
import com.kamesha.ecommerce.dto.PurchaseResponse;
import com.kamesha.ecommerce.model.Customer;
import com.kamesha.ecommerce.model.Order;
import com.kamesha.ecommerce.model.OrderItem;
import com.kamesha.ecommerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;


    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        //
        Order order = purchase.getOrder();

        //
        String orderTrackingNumber  = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        //
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item ));

        //
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        //
        Customer customer = purchase.getCustomer();
        customer.add(order);

        //
        customerRepository.save(customer);


        return  new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
