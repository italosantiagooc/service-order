package br.com.serviceordersystem.configs;

import br.com.serviceordersystem.services.DBService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    private final DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    public DevConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public boolean instanceDB() {

        if (ddl.equals("create")) {
            this.dbService.instanceDB();
        }
        return false;
    }
}
