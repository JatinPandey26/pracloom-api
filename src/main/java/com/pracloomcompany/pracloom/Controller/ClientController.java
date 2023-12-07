package com.pracloomcompany.pracloom.Controller;

import com.pracloomcompany.pracloom.Entities.Organization;
import com.pracloomcompany.pracloom.Mapper.OrganizationMapper;
import com.pracloomcompany.pracloom.Service.ClientService;
import com.pracloomcompany.pracloom.dto.OrganizationDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/organization")
public class ClientController extends BaseController {


    private final ClientService clientService;
    private final EntityManager entityManager;
    private final OrganizationMapper organizationMapper;
    @PostMapping("/new")
    public ResponseEntity<String> createOrganization(@RequestBody OrganizationDTO organizationDTO){
        this.clientService.createOrganization(organizationDTO);
        return ResponseEntity.ok("Organization created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDTO> getOrganization(@PathVariable Integer id){
        return ResponseEntity.ok(this.clientService.getOrganization(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrganization(@PathVariable Integer id, @RequestBody OrganizationDTO organizationDTO){
        this.clientService.updateOrganization(id, organizationDTO);
        return ResponseEntity.ok("Organization updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrganization(@PathVariable Integer id){
        this.clientService.deleteOrganization(id);
        return ResponseEntity.ok("Organization deleted successfully");
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<OrganizationDTO>> getOrganizationByCustomerId(@PathVariable Integer customerId){

        String query = "SELECT distinct o FROM Organization o WHERE o.initiated_by.id = :customerId";
        List<Organization> organizations = this.entityManager.createQuery(query, Organization.class)
                .setParameter("customerId", customerId)
                .getResultList();
        log.info("organizations : {}", organizations);
        return ResponseEntity.ok(this.organizationMapper.toDtoList(organizations));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrganizationDTO>> getAllOrganizations(){
        List<Organization> organizations = this.clientService.getAllOrganizations();
        return ResponseEntity.ok(this.organizationMapper.toDtoList(organizations));
    }



}
