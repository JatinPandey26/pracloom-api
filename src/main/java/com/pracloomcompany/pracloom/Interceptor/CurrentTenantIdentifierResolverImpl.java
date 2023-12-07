package com.pracloomcompany.pracloom.Interceptor;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    private final String defaultTenant ="base";

    @Override
    public @NonNull String resolveCurrentTenantIdentifier() {
        String t =  TenantContext.getCurrentTenant();
        log.info("TenantSchemaResolver : {}",t);
        if(t!=null){
            return t;
        } else {
            return defaultTenant;
        }
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}