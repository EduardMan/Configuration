package tech.itparklessons.configuration.config.kotlin;

import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;

public class KotlinSQLiteConnector {
    private final String login;
    private final String password;
    private final DataSource ds;

    public KotlinSQLiteConnector(@Value("${login}") String login, @Value("${password}") String password, DataSource ds) {
        this.login = login;
        this.password = password;
        this.ds = ds;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public DataSource getDs() {
        return ds;
    }
}