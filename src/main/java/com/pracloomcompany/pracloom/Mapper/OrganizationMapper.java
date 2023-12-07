package com.pracloomcompany.pracloom.Mapper;

import com.pracloomcompany.pracloom.Entities.Organization;
import com.pracloomcompany.pracloom.dto.OrganizationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    Organization toEntity(OrganizationDTO organizationDTO);

    OrganizationDTO toDto(Organization organization);

    List<OrganizationDTO> toDtoList(List<Organization> organizations);

}
