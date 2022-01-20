package es.edu.escuela_it.microservices.configuration;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ToString
public class ApplicationConfig {

    @Value("${app.name}")
    private String name;

    @Value("${app.year}")
    private Integer year;

    @Value("${app.edition}")
    private String edition;

    @Value("${app.countries}")
    private String[] countries;
}
