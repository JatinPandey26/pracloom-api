CREATE TABLE IF NOT EXISTS "pracloom-1001".DataSourceConfig (
                                  id SERIAL PRIMARY KEY,
                                  name VARCHAR(255),
                                  url VARCHAR(255),
                                  username VARCHAR(255),
                                  password VARCHAR(255),
                                  driverClassName VARCHAR(255),
                                  initialize BOOLEAN
);