package es.edu.escuela_it.microservices.controller;

import es.edu.escuela_it.microservices.configuration.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@PropertySource(value = "classpath:mensajes.properties") // Properties de archivo especifico
public class HolaMundoRest {

    // Recomendable usar slf4j ya que trabajamos con cualquier nivel de traza
    private Logger log = LoggerFactory.getLogger(HolaMundoRest.class);

    @Autowired
    private ApplicationConfig appConfig;

    @GetMapping("holamundo")
    public String saludo(){

        log.trace("Ejecutando hola mundo trace");
        log.debug("Ejecutando hola mundo debug");
        log.info("Ejecutando hola mundo info");
        log.warn("Ejecutando hola mundo warn");
        log.error("Ejecutando hola mundo error");

        //System.out.println(appConfig.toString()); // ApplicationConfig(name=Microservice Java Escuela IT, year=2020, edition=Community, countries=[es, ar, it, ca, br])
        return "Hola mundo Servicio rest Java";
    }
}
