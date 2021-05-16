package tech.itparklessons.configuration.config.programmatic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Getter
@Component
public class ProgrammaticSQLiteConnector {
    private final String login;
    private final String password;

//    @Autowired
    private final DataSource ds;
}
