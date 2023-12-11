package com.pracloomcompany.pracloom.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@Entity
@Table(name = "organization_tb")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String tenant_id;


    @NotBlank(message = "Organization name is required")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Organization number is required")
    private String number;
    @NotBlank(message = "Organization address is required")
    private String address;

    private String logo_url;
    private String logo_secret;
  
    @ManyToOne
    private Customer initiated_by;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    private boolean paid;

    @PrePersist
    public void prePersist() {
        this.creationDate = new Date();
        this.paid = false;
    }
}
