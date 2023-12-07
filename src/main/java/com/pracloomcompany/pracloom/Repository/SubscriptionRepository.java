package com.pracloomcompany.pracloom.Repository;

import com.pracloomcompany.pracloom.Entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
}
