package es.edu.escuela_it.microservices.services;

import es.edu.escuela_it.microservices.model.UserDTO;

public interface UserService {

    public UserDTO getUserById(Integer id);
}
