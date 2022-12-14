package es.edu.escuela_it.microservices.configuration;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ResourceMessageConfiguration implements WebMvcConfigurer {
    /**
     * Establece un archivo de mensajes por default
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); // Toma mensaje desde el archivo "messages.properties"
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /*
     * Proporciona la posibilidad de que los mensajes de los validator accedan al archivo de claves message
     */
    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

    /*
     * Establece un locale por default
     */
    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("es")); // en ingles por defecto | Internacionalizacion
        return localeResolver;
    }

    /*
     * Permite cambiar el local en cada llamada por el param lang
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        // Podemos permitir cambiar el lenguaje del mensaje con cada llamada al endpoint
        // suministrando el param "lang"
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}