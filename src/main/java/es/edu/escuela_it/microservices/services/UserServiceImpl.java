package es.edu.escuela_it.microservices.services;

import es.edu.escuela_it.microservices.dao.repositories.UserRepository;
import es.edu.escuela_it.microservices.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("BD") // Servicio que tiene un alias
@ConditionalOnProperty(prefix = "app", name = "edition", havingValue = "community") // Aqui lo elije por un archivo .properties
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserDTO> getUserById(Integer id) {
        Optional<UserDTO> userDTO = userRepository.findById(id);
        return userDTO;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<UserDTO> users = userRepository.findAll();
        return users;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        userDTO = userRepository.save(userDTO);
        return userDTO;
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
