package com.pracloomcompany.pracloom.Service;

import com.pracloomcompany.pracloom.Entities.Organization;
import com.pracloomcompany.pracloom.Mapper.OrganizationMapper;
import com.pracloomcompany.pracloom.Repository.OrganizationRepository;
import com.pracloomcompany.pracloom.dto.OrganizationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    public void createOrganization(OrganizationDTO organizationDTO){
        Organization organizationWithSameName = organizationRepository.findByName(organizationDTO.getName());
        if(organizationWithSameName != null){
            throw new RuntimeException("Organization with same name already exists");
        }
        Organization organization = this.organizationMapper.toEntity(organizationDTO);
        Organization savedOrganization =  organizationRepository.save(organization);
        log.info("organization saved with id : {}" , savedOrganization.getId());
    }

    public OrganizationDTO getOrganization(Integer id){
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new RuntimeException("Organization not found with id : " + id));
        return this.organizationMapper.toDto(organization);
    }

    public void updateOrganization(Integer id, OrganizationDTO organizationDTO){
        organizationRepository.findById(id).orElseThrow(() -> new RuntimeException("Organization not found with id : " + id));

        this.organizationRepository.save(this.organizationMapper.toEntity(organizationDTO));
    }

    public void deleteOrganization(Integer id){
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new RuntimeException("Organization not found with id : " + id));
        this.organizationRepository.delete(organization);
    }

    public List<Organization> getAllOrganizations() {
        return this.organizationRepository.findAll();
    }


    // Payment Services handling

    // after success payment, create subscription
}
