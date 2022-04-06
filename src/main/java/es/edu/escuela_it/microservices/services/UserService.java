package es.edu.escuela_it.microservices.services;

import es.edu.escuela_it.microservices.model.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<UserDTO> getUserById(Integer id);

    public List<UserDTO> listAllUsers(Pageable pageable);

    public UserDTO saveUser(UserDTO userDTO);

    public void deleteById(Integer id);
}
