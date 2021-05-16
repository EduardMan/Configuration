package tech.itparklessons.configuration.config.annotation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component("connector")
@NoArgsConstructor
@Getter
@PropertySource("classpath:db.properties")
public class AnnotationSQLiteConnector {
    @Value("${login}")
    private String login;
    @Value("${password}")
    private String password;
    private DataSource ds;

    @Autowired
    public AnnotationSQLiteConnector(DataSource ds) {
        this.ds = ds;
    }
}