package com.pracloomcompany.pracloom.Repository;

import com.pracloomcompany.pracloom.Entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Integer> {
    Organization findByName(String name);
}
