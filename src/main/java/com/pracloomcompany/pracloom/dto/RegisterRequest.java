package com.pracloomcompany.pracloom.dto;

import com.pracloomcompany.pracloom.Entities.Permissions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String first_name;
    private String middle_name;
    private String last_name;
    private Date dob;
    private String email;
    private String password;
    private String theme;
    private String country;
    private Date register_date;
    private String profile_picture_url;
    private String profile_picture_secret;
    private Permissions role;
}
