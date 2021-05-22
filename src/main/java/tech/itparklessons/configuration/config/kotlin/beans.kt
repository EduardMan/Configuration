package tech.itparklessons.configuration.config.kotlin;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.context.support.beans
import org.springframework.core.io.ClassPathResource

val beans = beans {
    bean {
        PropertySourcesPlaceholderConfigurer().apply {
            setLocation(ClassPathResource("db.properties"))
        }
    }
    bean<DataSource>()
    bean<KotlinSQLiteConnector>("connector")
}