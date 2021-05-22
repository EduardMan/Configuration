package tech.itparklessons.configuration.config.groovy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component("connector")
@RequiredArgsConstructor
@Getter
public class GroovySQLiteConnector {
    private final String login;
    private final String password;
    private final DataSource ds;
}