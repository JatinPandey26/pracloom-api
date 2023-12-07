package com.pracloomcompany.pracloom.dto;

import com.pracloomcompany.pracloom.Entities.Customer;
import lombok.Data;

@Data
public class OrganizationDTO {
        private String name;
        private String email;
        private String number;
        private String address;
        private String logo_url;
        private String logo_secret;
        private Customer initiated_by;
}
