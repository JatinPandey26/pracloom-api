package com.pracloomcompany.pracloom.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "subscription_type")
@Entity
@Data
public class SubscriptionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tenant_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int duration_in_months;

    @Column(nullable = false)
    private int price;


}
