import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import tech.itparklessons.configuration.config.groovy.DataSource
import tech.itparklessons.configuration.config.groovy.GroovySQLiteConnector

beans {
    propertyPlaceholderConfigurer PropertySourcesPlaceholderConfigurer, {
        location = 'classpath:db.properties'
    }

    dataSource(DataSource)
    connector(GroovySQLiteConnector, '${login}', '${password}', dataSource)
}