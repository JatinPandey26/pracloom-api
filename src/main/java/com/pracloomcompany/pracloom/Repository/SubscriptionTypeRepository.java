package com.pracloomcompany.pracloom.Repository;

import com.pracloomcompany.pracloom.Entities.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType, Integer>{
}
