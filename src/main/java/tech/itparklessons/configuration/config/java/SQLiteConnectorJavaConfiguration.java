package tech.itparklessons.configuration.config.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

@Configuration
public class SQLiteConnectorJavaConfiguration {
    @Bean("connector")
    JavaSQLiteConnector javaSQLiteConnector(DataSource dataSource) {
        return new JavaSQLiteConnector("app", "pass", dataSource);
    }

    @Bean
    DataSource dataSource() {
        return new SQLiteDataSource();
    }
}
