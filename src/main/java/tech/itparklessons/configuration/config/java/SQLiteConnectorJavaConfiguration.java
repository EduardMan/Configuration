package tech.itparklessons.configuration.config.java;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

@Configuration
public class SQLiteConnectorJavaConfiguration {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        val configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("db.properties"));
        return configurer;
    }

    @Bean("connector")
    JavaSQLiteConnector javaSQLiteConnector(@Value("${login}") String login, @Value("${password}") String password, DataSource dataSource) {
        return new JavaSQLiteConnector(login, password, dataSource);
    }

    @Bean
    DataSource dataSource() {
        return new SQLiteDataSource();
    }
}
