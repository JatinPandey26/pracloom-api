package com.pracloomcompany.pracloom.Mapper;

import com.pracloomcompany.pracloom.Entities.Organization;
import com.pracloomcompany.pracloom.dto.OrganizationDTO;

import com.pracloomcompany.pracloom.dto.OrganizationResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {


    @Mapping(target = "paid", ignore = true)
    @Mapping(target = "logo_url", ignore = true)
    @Mapping(target = "logo_secret", ignore = true)
    @Mapping(target = "initiated_by", ignore = true)
    @Mapping(target = "tenant_id", ignore = true)

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    Organization toEntity(OrganizationDTO organizationDTO);


    @Mapping(target = "logo", ignore = true)

    OrganizationDTO toDto(Organization organization);

    List<OrganizationDTO> toDtoList(List<Organization> organizations);


    OrganizationResponse toResponse(Organization organization);

}
