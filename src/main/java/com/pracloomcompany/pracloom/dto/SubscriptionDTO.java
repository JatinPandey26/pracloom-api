package com.pracloomcompany.pracloom.dto;

import com.pracloomcompany.pracloom.Entities.Organization;
import com.pracloomcompany.pracloom.Entities.SubscriptionType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class SubscriptionDTO {
    private String txn_razorpay_id;
    private SubscriptionType subscription_type;
    private Date created_at;
    private Date expires_at;
    private Organization organization;
}
