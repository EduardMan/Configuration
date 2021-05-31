package tech.itparklessons.configuration.config.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:db.properties")
public class AnnotationConfiguration {
    @Value("${login}")
    private String login;
    @Value("${password}")
    private String password;

    @Bean DataSource ds() {
        return new DataSource();
    }

    @Bean
    public AnnotationSQLiteConnector connector(DataSource ds) {
        return new AnnotationSQLiteConnector(login, password, ds);
    }
}
