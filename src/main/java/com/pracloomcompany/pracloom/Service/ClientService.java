package com.pracloomcompany.pracloom.Service;

import com.pracloomcompany.pracloom.Entities.Customer;
import com.pracloomcompany.pracloom.Entities.Organization;
import com.pracloomcompany.pracloom.Interceptor.TenantContext;
import com.pracloomcompany.pracloom.Mapper.OrganizationMapper;
import com.pracloomcompany.pracloom.Repository.OrganizationRepository;
import com.pracloomcompany.pracloom.Service.Impl.CloudnaryService;
import com.pracloomcompany.pracloom.dto.OrganizationDTO;
import com.pracloomcompany.pracloom.dto.OrganizationResponse;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    private final CloudnaryService cloudnaryService;
    private final EntityManager entityManager;

    public OrganizationResponse createOrganization(OrganizationDTO organizationDTO) throws IOException {
        Organization organizationWithSameName = organizationRepository.findByName(organizationDTO.getName());
        if(organizationWithSameName != null){
            log.info("organizationWithSameName : {}", organizationWithSameName);
            throw new RuntimeException("Organization with same name already exists");
        }

        Map image_data = this.cloudnaryService.uploadImage(organizationDTO.getLogo());
        log.info("image_data : {}", image_data);
        Organization organization = this.organizationMapper.toEntity(organizationDTO);
        organization.setInitiated_by((Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        organization.setTenant_id(TenantContext.getCurrentTenant());
        organization.setLogo_url((String) image_data.get("url"));
        organization.setLogo_secret((String) image_data.get("public_id"));
        Organization savedOrganization =  organizationRepository.save(organization);
        log.info("organization saved with id : {}" , savedOrganization.getId());
        return this.organizationMapper.toResponse(savedOrganization);
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

    public List<Organization> getAllOrganizationsWhichAreNotPaidYet() {
        String query = "select * from \"pracloom-1001\".organization_tb where initiated_by_id = :customer_id and paid = :paid_status ;";

        List<Organization> organizations = this.entityManager.createNativeQuery(query, Organization.class)
                .setParameter("customer_id", ((Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId())
                .setParameter("paid_status", false)
                .getResultList();

        log.info("organizations : {}", organizations);
        return organizations;
    }


    // Payment Services handling

    // after success payment, create subscription
}
