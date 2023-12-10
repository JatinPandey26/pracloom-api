package com.pracloomcompany.pracloom.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SubscriptionTypeRequest {
    private String name;
    private int duration_in_months;
    private int price;
}
