package tech.itparklessons.configuration.config.xml;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Getter
public class XMLSQLiteConnector {
    private final String login;
    private final String password;
    private final DataSource ds;
}