import tech.itparklessons.configuration.config.groovy.DataSource
import tech.itparklessons.configuration.config.groovy.GroovySQLiteConnector

Properties properties = new Properties()
File propertiesFile = new File(getClass().getResource('/db.properties').toURI())
propertiesFile.withInputStream {
    properties.load(it)
}

beans {
    dataSource(DataSource)
    connector(GroovySQLiteConnector, properties.login, properties.password, dataSource)
}