package tech.itparklessons.configuration.config.java;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Getter
public class JavaSQLiteConnector {
    private final String login;
    private final String password;
    private final DataSource ds;
}
