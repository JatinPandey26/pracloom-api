package com.pracloomcompany.pracloom.Service.Impl;

import com.pracloomcompany.pracloom.Entities.SubscriptionType;
import com.pracloomcompany.pracloom.Interceptor.TenantContext;
import com.pracloomcompany.pracloom.Repository.SubscriptionTypeRepository;
import com.pracloomcompany.pracloom.Service.SubscriptionTypeService;
import com.pracloomcompany.pracloom.dto.SubscriptionTypeRequest;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    private final SubscriptionTypeRepository subscriptionTypeRepository;
    private final EntityManager entityManager;
    @Value("${tenant.default}")
    private String defaultTenant;

    @Override
    public List<SubscriptionType> getSubscriptionTypes() {
        log.info("defaultTenant : {}",defaultTenant);
        String tenant = TenantContext.getCurrentTenant();
        String query = "SELECT * FROM subscription_type WHERE tenant_id IN (:defaultTenant , :tenant)";

        List<SubscriptionType> subscriptionTypes = this.entityManager.createNativeQuery(query, SubscriptionType.class)
                .setParameter("defaultTenant", defaultTenant)
                .setParameter("tenant", tenant)
                .getResultList();

        return subscriptionTypes;
    }

    @Override
    public void createSubscriptionType(SubscriptionTypeRequest subscriptionType) {
        String tenant = TenantContext.getCurrentTenant();
        String query = "INSERT INTO subscription_type (tenant_id, name, duration_in_months, price) VALUES ( :tenant, :name, :duration_in_months, :price)";

        this.entityManager.createNativeQuery(query)

                .setParameter("tenant", tenant)
                .setParameter("name", subscriptionType.getName())
                .setParameter("duration_in_months", subscriptionType.getDuration_in_months())
                .setParameter("price", subscriptionType.getPrice())
                .executeUpdate();
    }

    @Override
    public void deleteSubscriptionType(int id) {

    }
}
