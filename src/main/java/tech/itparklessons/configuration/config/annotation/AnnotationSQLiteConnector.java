package tech.itparklessons.configuration.config.annotation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;

@Getter
@RequiredArgsConstructor
public class AnnotationSQLiteConnector {
    private final String login;
    private final String password;
    private final DataSource ds;
}