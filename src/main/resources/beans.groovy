import org.springframework.core.io.ClassPathResource
import tech.itparklessons.configuration.config.groovy.GroovySQLiteConnector

Properties properties = new Properties()
File propertiesFile = new File(getClass().getResource('/db.properties').toURI())
propertiesFile.withInputStream {
    properties.load(it)
}

//def url = new ClassPathResource('db.properties').URL;
//def config = new ConfigSlurper().parse(url);

beans {
    connector(GroovySQLiteConnector, properties.login, properties.password)
}