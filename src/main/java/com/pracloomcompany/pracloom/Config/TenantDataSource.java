package com.pracloomcompany.pracloom.Config;

import com.pracloomcompany.pracloom.Entities.DataSourceConfig;
import com.pracloomcompany.pracloom.Repository.DataSourceConfigRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class TenantDataSource implements Serializable {

    private final HashMap<String, DataSource> dataSources = new HashMap<>();

    @Autowired
    private DataSourceConfigRepository configRepo;

    public DataSource getDataSource(String name) {
        if (dataSources.get(name) != null) {
            return dataSources.get(name);
        }
        DataSource dataSource = createDataSource(name);
        if (dataSource != null) {
            dataSources.put(name, dataSource);
        }
        return dataSource;
    }

    @PostConstruct
    public Map<String, DataSource> getAll() {
       try {

           List<DataSourceConfig> configList = configRepo.findAll();
           log.info("configList : {}",configList);
           Map<String, DataSource> result = new HashMap<>();
           for (DataSourceConfig config : configList) {
               DataSource dataSource = getDataSource(config.getName());
               if (dataSource != null) {
                   result.put(config.getName(), dataSource);
               }
           }
           return result;
       }
       catch (Exception e) {
           e.printStackTrace();
           throw e;
       }
    }

    private DataSource createDataSource(String name) {
        DataSourceConfig config = configRepo.findByName(name);
        if (config != null) {
            DataSourceBuilder<?> factory = DataSourceBuilder
                    .create().driverClassName(config.getDriverClassName())
                    .username(config.getUsername())
                    .password(config.getPassword())
                    .url(config.getUrl());
            DataSource ds = factory.build();
            return ds;
        }
        return null;
    }

}
