package es.edu.escuela_it.microservices.services;

import es.edu.escuela_it.microservices.model.UserDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary // Hay 2 implemencaciones, pero con esta anotacion elige esta clase como prioridad
@Service
@Qualifier("CLOUD")
@ConditionalOnProperty(prefix = "app", name = "edition", havingValue = "pro")
public class UserServiceCloudImpl implements UserService{

    @Override
    public UserDTO getUserById(Integer id) {
        return new UserDTO(2, "Alvaro");
    }
}
