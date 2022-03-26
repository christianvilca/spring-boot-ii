package es.edu.escuela_it.microservices.services;

import es.edu.escuela_it.microservices.client.UserClient;
import es.edu.escuela_it.microservices.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Primary // Hay 2 implemencaciones, pero con esta anotacion elige esta clase como prioridad
@Service
@Qualifier("CLOUD")
@ConditionalOnProperty(prefix = "app", name = "edition", havingValue = "pro")
public class UserServiceCloudImpl implements UserService{

    @Autowired
    private UserClient userClient;

    @Override
    public Optional<UserDTO> getUserById(Integer id) {

        UserDTO userDTO = userClient.getUser(id);

        return Optional.ofNullable(userDTO);
    }
}
