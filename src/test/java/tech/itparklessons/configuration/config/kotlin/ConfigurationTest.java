package tech.itparklessons.configuration.config.kotlin;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import tech.itparklessons.configuration.config.annotation.AnnotationSQLiteConnector;
import tech.itparklessons.configuration.config.groovy.GroovySQLiteConnector;
import tech.itparklessons.configuration.config.java.JavaSQLiteConnector;
import tech.itparklessons.configuration.config.java.SQLiteConnectorJavaConfiguration;
import tech.itparklessons.configuration.config.programmatic.DataSource;
import tech.itparklessons.configuration.config.programmatic.ProgrammaticSQLiteConnector;
import tech.itparklessons.configuration.config.xml.XMLSQLiteConnector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConfigurationTest {
    @Test
    void xmlConfig() {
        val context = new GenericApplicationContext();
        new XmlBeanDefinitionReader(context).loadBeanDefinitions("context.xml");
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
        val context = new AnnotationConfigApplicationContext(SQLiteConnectorJavaConfiguration.class);
        val bean = context.getBean("connector", JavaSQLiteConnector.class);

        assertNotNull(bean.getDs());
        assertEquals("app", bean.getLogin());
        assertEquals("pass", bean.getPassword());
    }

    @Test
    void programmaticConfig() {
        val context = new GenericApplicationContext();
        context.registerBean(PropertySourcesPlaceholderConfigurer.class, () -> {
            val configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setLocation(new ClassPathResource("db.properties"));
            return configurer;
        });
        RuntimeBeanReference dataSource = new RuntimeBeanReference("dataSource");
        context.registerBean("connector", ProgrammaticSQLiteConnector.class, "${login}", "${password}", dataSource);
        context.registerBean("dataSource", DataSource.class);
        context.refresh();
        val bean = context.getBean("connector", ProgrammaticSQLiteConnector.class);

        assertNotNull(bean.getDs());
        assertEquals("app", bean.getLogin());
        assertEquals("pass", bean.getPassword());
    }

    @Test
    void groovyConfig() {
        val context = new GenericGroovyApplicationContext();
        val reader = new GroovyBeanDefinitionReader(context);
        reader.loadBeanDefinitions("beans.groovy");
        context.refresh();

        val bean = context.getBean("connector", GroovySQLiteConnector.class);
        assertNotNull(bean.getDs());
        assertEquals("app", bean.getLogin());
        assertEquals("pass", bean.getPassword());

    }

    @Test
    void kotlinContext() {
        val factory = new DefaultListableBeanFactory();
        factory.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
        val context = new GenericApplicationContext(factory);
        BeansKt.getBeans().initialize(context);
        context.refresh();

        val bean = context.getBean("connector", KotlinSQLiteConnector.class);
        assertNotNull(bean.getDs());
        assertEquals("app", bean.getLogin());
        assertEquals("pass", bean.getPassword());
    }
}
