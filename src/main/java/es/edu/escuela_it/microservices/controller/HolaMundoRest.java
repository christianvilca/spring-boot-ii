package es.edu.escuela_it.microservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundoRest {

    @GetMapping("holamundo")
    public String saludo(){
        return "Hola mundo Servicio rest Java";
    }
}
