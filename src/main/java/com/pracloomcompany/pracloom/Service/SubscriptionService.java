package com.pracloomcompany.pracloom.Service;

import com.pracloomcompany.pracloom.Entities.Subscription;

import com.pracloomcompany.pracloom.Entities.SubscriptionType;

import com.pracloomcompany.pracloom.Mapper.SubscriptionMapper;
import com.pracloomcompany.pracloom.Repository.SubscriptionRepository;
import com.pracloomcompany.pracloom.dto.SubscriptionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    public int createSubscription(SubscriptionDTO subscriptionDTO){
        Subscription subscription = subscriptionMapper.toEntity(subscriptionDTO);
        subscriptionRepository.save(subscription);
        return subscription.getId();
    }
}
