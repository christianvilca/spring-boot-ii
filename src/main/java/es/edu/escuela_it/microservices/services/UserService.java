package es.edu.escuela_it.microservices.services;

import es.edu.escuela_it.microservices.model.UserDTO;

import java.util.Optional;

public interface UserService {

    public Optional<UserDTO> getUserById(Integer id);
}
