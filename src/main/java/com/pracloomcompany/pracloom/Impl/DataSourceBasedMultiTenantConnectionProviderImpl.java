package com.pracloomcompany.pracloom.Impl;

import com.pracloomcompany.pracloom.Config.TenantDataSource;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j

public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    private static final String DEFAULT_TENANT_ID = "pracloom-1001";
    @Autowired
    private DataSource defaultDS;

    @Autowired
    private ApplicationContext context;

    private Map<String, DataSource> map = new HashMap<>();

    boolean init = false;

    @PostConstruct
    public void load() {
        map.put(DEFAULT_TENANT_ID, defaultDS);
    }

    @Override
    protected DataSource selectAnyDataSource() {
        return map.get(DEFAULT_TENANT_ID);
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {


        if (!init) {

            init = true;
            TenantDataSource tenantDataSource = context.getBean(TenantDataSource.class);
            map.putAll(tenantDataSource.getAll());

        }
        log.info("tenantIdentifier : {}",tenantIdentifier);
        log.info("selectDataSource : {}",map.get(tenantIdentifier));
        return map.get(tenantIdentifier) != null ? map.get(tenantIdentifier) : map.get(DEFAULT_TENANT_ID);
    }
}