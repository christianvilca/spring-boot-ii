package es.edu.escuela_it.microservices.services;

import es.edu.escuela_it.microservices.model.UserDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@Qualifier("BD") // Servicio que tiene un alias
@ConditionalOnProperty(prefix = "app", name = "edition", havingValue = "community") // Aqui lo elije por un archivo .properties
public class UserServiceImpl implements UserService {

    public UserDTO getUserById(Integer id) {
        return new UserDTO(1, "Rafael");
    }
}
