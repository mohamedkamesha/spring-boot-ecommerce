package com.kamesha.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
@Setter
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "customer" ,cascade = CascadeType.ALL)
    private Set<Order> orders = new LinkedHashSet<>();

    public void add(Order order){

        if (order != null){
            if (orders == null){
                orders = new HashSet<>();}
            orders.add(order);
            order.setCustomer(this);
        }
    }


}