package com.kamesha.ecommerce.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    @Column(name = "total_price", precision = 19, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id",referencedColumnName = "id")
    private Address billingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id",referencedColumnName = "id")
    private Address shippingAddress;

    @Column(name = "status", length = 128)
    private String status;

    @Column(name = "date_created")
    @CreationTimestamp
    private Instant dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Instant lastUpdated;

    @OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new LinkedHashSet<>();

    public void add(OrderItem item){

        if(item != null){
            if(orderItems == null){
                orderItems = new HashSet<>();}
            orderItems.add(item);
            item.setOrder(this);
        }
    }


}