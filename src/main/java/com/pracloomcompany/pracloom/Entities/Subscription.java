package com.pracloomcompany.pracloom.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Table
@Entity(name = "subscription")
@Data
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String txn_razorpay_id;

    @ManyToOne
    private SubscriptionType subscription_type;

    private Date created_at;
    private Date expires_at;

    @OneToOne
    private Organization organization;
}
