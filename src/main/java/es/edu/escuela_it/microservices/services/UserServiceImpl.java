package es.edu.escuela_it.microservices.services;

import es.edu.escuela_it.microservices.model.UserDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("BD") // Servicio que tiene un alias
@ConditionalOnProperty(prefix = "app", name = "edition", havingValue = "community") // Aqui lo elije por un archivo .properties
public class UserServiceImpl implements UserService {

    public Optional<UserDTO> getUserById(Integer id) {
        UserDTO userDTO = new UserDTO(1, "Rafael");
        return Optional.ofNullable(userDTO);
    }
}
