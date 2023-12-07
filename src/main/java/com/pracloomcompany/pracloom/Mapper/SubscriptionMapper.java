package com.pracloomcompany.pracloom.Mapper;

import com.pracloomcompany.pracloom.Entities.Subscription;
import com.pracloomcompany.pracloom.dto.SubscriptionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(target = "id", ignore = true)
    Subscription toEntity(SubscriptionDTO subscriptionDTO);

    SubscriptionDTO toDTO(Subscription subscription);
}
