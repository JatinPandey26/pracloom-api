package com.pracloomcompany.pracloom.Entities;

import jakarta.persistence.*;
import lombok.NonNull;

import java.util.Date;

@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String first_name;
    private String middle_name;
    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    private Date dob;

    @Column(nullable = false)
    private String email;

    private String theme;

    private Date register_date;

    private String profile_picture_url;
    private String profile_picture_secret;
    @Column(nullable = false)
    private String role;

}
