package com.pracloomcompany.pracloom.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "instance")
public class Instance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tenant_id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    private String logo_url;
    private String logo_secret;

    private boolean isCourses;
    private boolean isPractice;
    private boolean isChapterAssessment;

    private boolean isTrialAvailable;

    @OneToOne
    private Subscription subscription;

    @OneToOne
    private Organization organization;

    @OneToMany
    private List<SubscriptionType> subscriptionType;


    @PrePersist
    public void setTenant_id() {
        this.tenant_id = UUID.randomUUID().toString().replace("-", "");
    }
}
