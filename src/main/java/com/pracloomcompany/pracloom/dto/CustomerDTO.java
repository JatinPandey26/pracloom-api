package com.pracloomcompany.pracloom.dto;


import lombok.Data;

import java.util.Date;
@Data
public class CustomerDTO {
    private int id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private Date dob;
    private String email;
    private String theme;
    private Date register_date;
    private String country;
    private String profile_picture_url;
    private String profile_picture_secret;

}
