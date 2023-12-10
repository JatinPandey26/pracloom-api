package com.pracloomcompany.pracloom.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class OrganizationResponse {
    private int id;
    private String name;
    private String email;
    private String number;
    private String address;
}
