package es.edu.escuela_it.microservices.controller;

import es.edu.escuela_it.microservices.configuration.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@PropertySource(value = "classpath:mensajes.properties") // Properties de archivo especifico
public class HolaMundoRest {

    @Autowired
    private ApplicationConfig appConfig;

    @GetMapping("holamundo")
    public String saludo(){

        System.out.println(appConfig.toString()); // ApplicationConfig(name=Microservice Java Escuela IT, year=2020, edition=Community, countries=[es, ar, it, ca, br])
        return "Hola mundo Servicio rest Java";
    }
}
