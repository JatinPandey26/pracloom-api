package com.pracloomcompany.pracloom.Service;

import com.pracloomcompany.pracloom.Entities.SubscriptionType;
import com.pracloomcompany.pracloom.dto.SubscriptionTypeRequest;

import java.util.List;

public interface SubscriptionTypeService {
    List<SubscriptionType> getSubscriptionTypes();


    void createSubscriptionType(SubscriptionTypeRequest subscriptionType);

    void deleteSubscriptionType(int id);
}
