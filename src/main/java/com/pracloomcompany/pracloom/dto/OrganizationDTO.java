package com.pracloomcompany.pracloom.dto;

import com.pracloomcompany.pracloom.Entities.Customer;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class OrganizationDTO {
        private String name;
        private String email;
        private String number;
        private String address;
        private MultipartFile logo;
}
