import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;
import tech.itparklessons.configuration.config.annotation.AnnotationSQLiteConnector;
import tech.itparklessons.configuration.config.groovy.GroovySQLiteConnector;
import tech.itparklessons.configuration.config.java.JavaSQLiteConnector;
import tech.itparklessons.configuration.config.programmatic.DataSource;
import tech.itparklessons.configuration.config.programmatic.ProgrammaticSQLiteConnector;
import tech.itparklessons.configuration.config.xml.XMLSQLiteConnector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConfigurationTest {
    @Test
    void xmlConfig() {
        val context = new GenericApplicationContext();
        new XmlBeanDefinitionReader(context)
                .loadBeanDefinitions("context.xml");
        context.refresh();
        val bean = context.getBean("connector", XMLSQLiteConnector.class);

        assertNotNull(bean.getDs());
        assertEquals("app", bean.getLogin());
        assertEquals("pass", bean.getPassword());
    }

    @Test
    void annotationConfig() {
        val context = new AnnotationConfigApplicationContext("tech.itparklessons.configuration.config.annotation");
        val bean = context.getBean("connector", AnnotationSQLiteConnector.class);

        assertNotNull(bean.getDs());
        assertEquals("app", bean.getLogin());
        assertEquals("pass", bean.getPassword());
    }

    @Test
    void javaConfig() {
        val context = new AnnotationConfigApplicationContext("tech.itparklessons.configuration.config.java");
        val bean = context.getBean("connector", JavaSQLiteConnector.class);

        assertNotNull(bean.getDs());
        assertEquals("app", bean.getLogin());
        assertEquals("pass", bean.getPassword());
    }

    @Test
    void programmaticConfig() {
        val context = new GenericApplicationContext();
        context.registerBean("dataSource", DataSource.class);
        context.registerBean("connector", ProgrammaticSQLiteConnector.class, "app", "pass", context.getBeanDefinition("dataSource"));
        context.refresh();

        val bean = context.getBean("connector", ProgrammaticSQLiteConnector.class);
        assertNotNull(bean.getDs());
        assertEquals("app", bean.getLogin());
        assertEquals("pass", bean.getPassword());
    }

    @Test
    void groovyConfig() {
        val context = new GenericGroovyApplicationContext("beans.groovy");

        val bean = context.getBean("connector", GroovySQLiteConnector.class);
//        assertNotNull(bean.getDs());
        assertEquals("app", bean.getLogin());
        assertEquals("pass", bean.getPassword());

    }
}
