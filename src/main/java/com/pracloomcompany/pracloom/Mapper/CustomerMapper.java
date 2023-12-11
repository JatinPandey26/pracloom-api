package com.pracloomcompany.pracloom.Mapper;

import com.pracloomcompany.pracloom.Entities.Customer;

import com.pracloomcompany.pracloom.dto.CustomerDTO;

import com.pracloomcompany.pracloom.dto.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "id", ignore = true)
    Customer toEntity(RegisterRequest request);


    CustomerDTO toDto(Customer customer);

}
